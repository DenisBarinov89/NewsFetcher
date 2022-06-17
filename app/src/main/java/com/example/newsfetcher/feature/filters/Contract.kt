package com.example.newsfetcher.feature.filters

import com.example.newsfetcher.GET_ARTICLES_BY_POPULARITY
import com.example.newsfetcher.GET_ARTICLES_BY_PUBLISHED_AT
import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.domain.ArticleModel

data class FiltersViewState(
    val isFiltersEnabled: Boolean,
    val filterArticles: List<ArticleModel>,
    val articlesShown: List<ArticleModel>
)

sealed class UIEvent : Event {
    data class FilterSortByPopularityClicked(val q: String) : UIEvent()
    data class FilterSortByTitleClicked(val q: String) : UIEvent()
    data class FilterSortByDateClicked(val q: String) : UIEvent()
    data class ShowResultDateFilterButtonClicked(val dateFrom: String, val dateTo: String, val q: String) : UIEvent()
    data class OnFindArticlesButtonClicked(val q: String) : UIEvent()
}

sealed class DataEvent : Event {
    data class LoadSortedArticles(val sortBy: String = GET_ARTICLES_BY_POPULARITY, val q: String) : DataEvent()
    data class LoadFilterArticlesByDate(val dateFrom: String, val dateTo: String, val q: String) : DataEvent()
    data class OnLoadSortedArticlesSucceed(val filterArticles: List<ArticleModel>) : DataEvent()
    data class LoadArticlesByRequest(val q: String) : DataEvent()
}