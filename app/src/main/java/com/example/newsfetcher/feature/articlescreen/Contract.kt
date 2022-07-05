package com.example.newsfetcher.feature.articlescreen

import android.widget.ImageView
import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.domain.ArticleModel

data class ViewState(
    val articleTitle: String?,
    val articleDescription: String?,
    val articleContent: String?,
    val articleUrlToImage: String?,
    val articleLink: String?
)

sealed class UiEvent() : Event {
}

sealed class DataEvent() : Event {
    data class ShowArticle(val currentArticle: ArticleModel) : DataEvent()
}