package com.example.newsfetcher.feature.filters

import androidx.lifecycle.viewModelScope
import com.example.newsfetcher.GET_ARTICLES_BY_POPULARITY
import com.example.newsfetcher.GET_ARTICLES_BY_PUBLISHED_AT
import com.example.newsfetcher.SORT_TITLE_ASCENDING
import com.example.newsfetcher.base.BaseViewModel
import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.domain.ArticlesInteractor
import kotlinx.coroutines.launch

class FilterArticlesViewModel(
    private val interactor: ArticlesInteractor
) : BaseViewModel<FiltersViewState>() {

//    init {
//        processDataEvent(DataEvent.LoadFilterArticles)
//    }

    override fun initialViewState(): FiltersViewState =
        FiltersViewState(filterArticles = emptyList(), articlesShown = emptyList())

    override fun reduce(event: Event, previousState: FiltersViewState): FiltersViewState? {

        when (event) {



            is UIEvent.FilterSortByDateClicked -> {
                processDataEvent(DataEvent.LoadSortedArticles(sortBy = GET_ARTICLES_BY_PUBLISHED_AT))
            }
            is UIEvent.FilterSortByPopularityClicked -> {
                processDataEvent(DataEvent.LoadSortedArticles(sortBy = GET_ARTICLES_BY_POPULARITY))
            }
            is UIEvent.FilterSortByTitleClicked -> {
                processDataEvent(DataEvent.LoadSortedArticles(sortBy = SORT_TITLE_ASCENDING))
            }
            is UIEvent.ShowResultDateFilterButtonClicked -> {
                processDataEvent(DataEvent.LoadFilterArticlesByDate(dateFrom = event.dateFrom, dateTo = event.dateTo))
            }




            is DataEvent.LoadSortedArticles -> {
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

            is DataEvent.LoadFilterArticlesByDate -> {
                viewModelScope.launch {
                    interactor.getArticlesFilterByDate(dateFrom = event.dateFrom, dateTo = event.dateTo).fold(
                        onError = {},
                        onSuccess = {
                            processDataEvent(DataEvent.OnLoadSortedArticlesSucceed(it))
                        }
                    )

                }

            }


            is DataEvent.OnLoadSortedArticlesSucceed -> {  //вывод полученных статей на экран
                return previousState.copy(articlesShown = event.filterArticles)
            }

//            is UIEvent.OnTestPreviousViewState -> {
//                return previousState.copy(articlesShown = previousState.filterArticles.sortedBy { it.title })
//            }
        }
        return null
    }
}