package com.example.newsfetcher.feature.presentation

import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.domain.ArticleModel

data class ViewState(
    val articles: List<ArticleModel>
)

sealed class DataEvent : Event {

    object LoadArticles : DataEvent()
    data class onLoadArticlesSucceed(val articles: List<ArticleModel>) : DataEvent()


}