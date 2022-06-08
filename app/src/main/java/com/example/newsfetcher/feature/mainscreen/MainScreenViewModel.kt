package com.example.newsfetcher.feature.mainscreen

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

    //запуск события при инициализации ВьюМодели
    init {
        processDataEvent(DataEvent.LoadArticles)
    }


    override fun initialViewState() = ViewState(articles = emptyList(), articlesShown = emptyList(), isSearchEnabled = false)

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
                viewModelScope.launch {
                    bookmarksInteractor.create(previousState.articlesShown[event.index])
                }
                return null
            }
            is UIEvent.OnSearchButtonClicked -> {
                return previousState.copy(articlesShown = if (previousState.isSearchEnabled) previousState.articles else previousState.articlesShown, isSearchEnabled = !previousState.isSearchEnabled)
            }
            is UIEvent.OnSearchEdit -> {
                return previousState.copy(articlesShown = previousState.articles.filter {
                    it.title.contains(event.text)
                })
            }
            else -> return null
        }
    }
}