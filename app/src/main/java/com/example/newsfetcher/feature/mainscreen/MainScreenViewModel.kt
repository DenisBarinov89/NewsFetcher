package com.example.newsfetcher.feature.mainscreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsfetcher.base.BaseViewModel
import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.bookmarks.domain.BookmarksInteractor
import com.example.newsfetcher.feature.domain.ArticlesInteractor
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val interactor: ArticlesInteractor,
    private val bookmarksInteractor: BookmarksInteractor
) : BaseViewModel<ViewState>() {

    //запуск события при инициализации ВьюМодели, подгружаем наши новости
    init {
        processDataEvent(DataEvent.LoadArticles)
    }

    override fun initialViewState() = ViewState(articles = emptyList(), articlesShown = emptyList())

    override fun reduce(event: Event, previousState: ViewState): ViewState? {

        when (event) {
            is DataEvent.LoadArticles -> {
                viewModelScope.launch {
                    interactor.getArticles().fold(
                        onError = {},
                        onSuccess = {
                            processDataEvent(DataEvent.OnLoadArticlesSucceed(it))
                        }
                    )
                }
                return null
            }
            is DataEvent.OnLoadArticlesSucceed -> {
                return previousState.copy(articles = event.articles, articlesShown = event.articles)
            }
            is UIEvent.OnArticleClicked -> {
                previousState.articles[event.index].bookmarkAddedFlag = true
                viewModelScope.launch {
                    bookmarksInteractor.create(previousState.articlesShown[event.index])
                }
                return null
            }
            else -> return null
        }
    }
}