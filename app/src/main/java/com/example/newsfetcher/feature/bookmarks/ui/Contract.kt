package com.example.newsfetcher.feature.bookmarks.ui

import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.domain.ArticleModel


data class ViewState(
    val bookmarksArticle: List<ArticleModel>,
    var isBookmarksEmpty: Boolean
)

sealed class UiEvent() : Event {
    data class OnDeleteFromBookmarksClicked(val index: Int) : UiEvent()
}

sealed class DataEvent() : Event {
    object LoadBookmarks : DataEvent()
    data class onSuccessBookmarksLoaded(val bookmarksArticle: List<ArticleModel>) : DataEvent()
    data class onFailedBookmarksLoaded(val throwable: Throwable) : DataEvent()
}