package com.example.newsfetcher.feature.filters

import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.domain.ArticleModel

data class FiltersViewState(
    val filterArticles: List<ArticleModel>,
    val articlesShown: List<ArticleModel>
)

sealed class UIEvent : Event {

    object OnTestPreviousViewState : UIEvent()
    object FilterSortByPopularityClicked : UIEvent()
    object FilterSortByTitleClicked : UIEvent()
    object FilterSortByDateClicked : UIEvent()
}

sealed class DataEvent : Event {

    data class LoadFilterArticles(val sortBy: String) : DataEvent()
    data class OnLoadSortedArticlesSucceed(val filterArticles: List<ArticleModel>) : DataEvent()
}