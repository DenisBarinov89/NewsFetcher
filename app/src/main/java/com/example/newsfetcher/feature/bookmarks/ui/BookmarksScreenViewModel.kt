package com.example.newsfetcher.feature.bookmarks.ui

import androidx.lifecycle.viewModelScope
import com.example.newsfetcher.base.BaseViewModel
import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.bookmarks.domain.BookmarksInteractor
import kotlinx.coroutines.launch


class BookmarksScreenViewModel(private val interactor: BookmarksInteractor) :
    BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.LoadBookmarks)
    }

    override fun initialViewState(): ViewState = ViewState(bookmarksArticle = emptyList(), isBookmarksEmpty = true)

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is DataEvent.LoadBookmarks -> {
                viewModelScope.launch {
                    interactor.read().fold(
                        onError = {
                        },
                        onSuccess = {
                            processDataEvent(DataEvent.onSuccessBookmarksLoaded(it))
                        }
                    )
                }
                return null
            }
            is DataEvent.onSuccessBookmarksLoaded -> {
                return previousState.copy(bookmarksArticle = event.bookmarksArticle, isBookmarksEmpty = event.bookmarksArticle.isEmpty())
            }
            is UiEvent.OnDeleteFromBookmarksClicked -> {
                viewModelScope.launch {
                    interactor.delete(previousState.bookmarksArticle[event.index])
                    processDataEvent(DataEvent.LoadBookmarks)
                }
                return null
            }
            else -> return null
        }
    }
}