package com.example.newsfetcher.feature.filters

import com.example.newsfetcher.GET_ARTICLES_BY_POPULARITY
import com.example.newsfetcher.GET_ARTICLES_BY_PUBLISHED_AT
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
    data class ShowResultDateFilterButtonClicked(val dateFrom: String, val dateTo: String) : UIEvent()

}

sealed class DataEvent : Event {

    data class LoadSortedArticles(val sortBy: String = GET_ARTICLES_BY_POPULARITY) : DataEvent()
    data class LoadFilterArticlesByDate(val sortBy: String = GET_ARTICLES_BY_PUBLISHED_AT, val dateFrom: String, val dateTo: String) : DataEvent()
    data class OnLoadSortedArticlesSucceed(val filterArticles: List<ArticleModel>) : DataEvent()
}