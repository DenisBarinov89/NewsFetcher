package com.example.newsfetcher.feature.filters

import android.provider.ContactsContract
import androidx.lifecycle.viewModelScope
import com.example.newsfetcher.GET_ARTICLES_BY_POPULARITY
import com.example.newsfetcher.GET_ARTICLES_BY_PUBLISHED_AT
import com.example.newsfetcher.GET_ARTICLES_BY_RELEVANCY
import com.example.newsfetcher.SORT_TITLE_ASCENDING
import com.example.newsfetcher.base.BaseViewModel
import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.bookmarks.domain.BookmarksInteractor
import com.example.newsfetcher.feature.domain.ArticlesInteractor
import com.example.newsfetcher.feature.mainscreen.ViewState
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import kotlin.concurrent.thread

class FilterArticlesViewModel(
    private val interactor: ArticlesInteractor
) : BaseViewModel<FiltersViewState>() {

//    init {
//        processDataEvent(DataEvent.LoadArticlesByPopularity)
//    }

    override fun initialViewState(): FiltersViewState =
        FiltersViewState(filterArticles = emptyList(), articlesShown = emptyList())

    override fun reduce(event: Event, previousState: FiltersViewState): FiltersViewState? {

        when (event) {



            is UIEvent.FilterSortByDateClicked -> {
                processDataEvent(DataEvent.LoadFilterArticles(sortBy = GET_ARTICLES_BY_PUBLISHED_AT))
            }
            is UIEvent.FilterSortByPopularityClicked -> {
                processDataEvent(DataEvent.LoadFilterArticles(sortBy = GET_ARTICLES_BY_POPULARITY))
            }
            is UIEvent.FilterSortByTitleClicked -> {
                processDataEvent(DataEvent.LoadFilterArticles(sortBy = SORT_TITLE_ASCENDING))
            }

            is DataEvent.LoadFilterArticles -> {
                viewModelScope.launch {
                    interactor.getArticlesSortBy(event.sortBy).fold(
                        onError = {},
                        onSuccess = {
                            if (event.sortBy == SORT_TITLE_ASCENDING)
                            processDataEvent(DataEvent.OnLoadSortedArticlesSucceed(it.sortedBy { it.title }))
                            else
                            processDataEvent(DataEvent.OnLoadSortedArticlesSucceed(it)) }
                    )
                }
            }

            is DataEvent.OnLoadSortedArticlesSucceed -> {
                return previousState.copy(articlesShown = event.filterArticles)
            }

//            is UIEvent.OnTestPreviousViewState -> {
//                return previousState.copy(articlesShown = previousState.filterArticles.sortedBy { it.title })
//            }
        }
        return null
    }
}