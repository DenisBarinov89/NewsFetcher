package com.example.newsfetcher.feature.filters

import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.domain.ArticleModel

data class FiltersViewState(
    val filterArticles: List<ArticleModel>,
    val articlesShown: List<ArticleModel>
)

sealed class UIEvent : Event {

    object OnTestPreviousViewState : UIEvent()


}

sealed class DataEvent : Event {

    object LoadArticlesByPopularity : DataEvent()
    data class OnLoadArticlesByPopularitySucceed(val filterArticles: List<ArticleModel>) : DataEvent()

    object LoadArticlesSortingByTitle : DataEvent()
    data class OnLoadArticlesByTitleSucceed(val filterArticles: List<ArticleModel>) : DataEvent()




}