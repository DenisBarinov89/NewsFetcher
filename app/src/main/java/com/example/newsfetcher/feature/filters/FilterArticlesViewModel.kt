package com.example.newsfetcher.feature.filters

import android.provider.ContactsContract
import androidx.lifecycle.viewModelScope
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

    init {
        processDataEvent(DataEvent.LoadArticlesByPopularity)
    }

    override fun initialViewState(): FiltersViewState =
        FiltersViewState(filterArticles = emptyList(), articlesShown = emptyList())

    override fun reduce(event: Event, previousState: FiltersViewState): FiltersViewState? {

        when (event) {

            is DataEvent.LoadArticlesByPopularity -> {
                viewModelScope.launch {
                    interactor.getArticlesSortedByPopularity().fold(
                        onError = {},
                        onSuccess = { processDataEvent(DataEvent.OnLoadArticlesByPopularitySucceed(it)) }
                    )

                }
            }
            is DataEvent.LoadArticlesSortingByTitle -> {
                viewModelScope.launch {
                    interactor.getArticlesSortedByPopularity().fold(
                        onError = {},
                        onSuccess = { processDataEvent(DataEvent.OnLoadArticlesByTitleSucceed(it)) }
                    )

                }
            }

            is DataEvent.OnLoadArticlesByPopularitySucceed -> {
                return previousState.copy(articlesShown = event.filterArticles)
            }
            is DataEvent.OnLoadArticlesByTitleSucceed -> {
                return previousState.copy(articlesShown = event.filterArticles.sortedBy {
                    it.title
                })
            }

            is UIEvent.OnTestPreviousViewState -> {
                return previousState.copy(articlesShown = previousState.filterArticles.sortedBy { it.title })
            }
        }

        return null

    }
}