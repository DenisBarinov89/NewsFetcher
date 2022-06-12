package com.example.newsfetcher.feature.filters

import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.domain.ArticleModel

data class FiltersViewState(
    val articlesShown: List<ArticleModel>,
    val articles: List<ArticleModel>
)

sealed class UIEvent : Event {



}

sealed class DataEvent : Event {


}