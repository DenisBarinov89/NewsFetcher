package com.example.newsfetcher.feature.filters

import androidx.lifecycle.viewModelScope
import com.example.newsfetcher.GET_ARTICLES_BY_POPULARITY
import com.example.newsfetcher.GET_ARTICLES_BY_PUBLISHED_AT
import com.example.newsfetcher.SORT_DATE_ASCENDING
import com.example.newsfetcher.SORT_TITLE_ASCENDING
import com.example.newsfetcher.base.BaseViewModel
import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.domain.ArticlesInteractor
import kotlinx.coroutines.launch

class FilterArticlesViewModel(
    private val interactor: ArticlesInteractor
) : BaseViewModel<FiltersViewState>() {

    override fun initialViewState(): FiltersViewState =
        FiltersViewState(filterArticles = emptyList(), articlesShown = emptyList(), isFiltersEnabled = false)

    override fun reduce(event: Event, previousState: FiltersViewState): FiltersViewState? {

        when (event) {
            is UIEvent.OnFindArticlesButtonClicked -> {
                processDataEvent(DataEvent.LoadArticlesByRequest(q = event.q))
            }
            is UIEvent.FilterSortByDateClicked -> {
                return previousState.copy(articlesShown = previousState.articlesShown.sortedBy { it.publishedAt })
            }
            is UIEvent.FilterSortByPopularityClicked -> {
                processDataEvent(DataEvent.LoadSortedArticles(sortBy = GET_ARTICLES_BY_POPULARITY, q = event.q))
            }
            is UIEvent.FilterSortByTitleClicked -> {
                return previousState.copy(articlesShown = previousState.articlesShown.sortedBy { it.title })
            }
            is UIEvent.ShowResultDateFilterButtonClicked -> {
                processDataEvent(DataEvent.LoadFilterArticlesByDate(dateFrom = event.dateFrom, dateTo = event.dateTo, q = event.q))
            }
            is DataEvent.LoadSortedArticles -> {
                viewModelScope.launch {
                    interactor.getArticlesSortBy(event.sortBy, event.q).fold(
                        onError = {},
                        onSuccess = { processDataEvent(DataEvent.OnLoadSortedArticlesSucceed(it)) }
                    )
                }
            }
            is DataEvent.LoadFilterArticlesByDate -> {
                viewModelScope.launch {
                    interactor.getArticlesFilterByDate(dateFrom = event.dateFrom, dateTo = event.dateTo, event.q).fold(
                        onError = {},
                        onSuccess = {
                            processDataEvent(DataEvent.OnLoadSortedArticlesSucceed(it))
                        }
                    )
                }
            }
            is DataEvent.LoadArticlesByRequest -> {
                viewModelScope.launch {
                    interactor.getArticlesSortBy(sortBy = SORT_DATE_ASCENDING, q = event.q).fold(
                        onError = {},
                        onSuccess = {
                            processDataEvent(DataEvent.OnLoadSortedArticlesSucceed(it))
                        }
                    )
                }
            }
            is DataEvent.OnLoadSortedArticlesSucceed -> {  //вывод полученных статей на экран
                return previousState.copy(articlesShown = event.filterArticles, isFiltersEnabled = true)
            }
        }
       return null
    }
}