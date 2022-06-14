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

}

sealed class DataEvent : Event {


    data class OnLoadArticlesByPopularitySucceed(val filterArticles: List<ArticleModel>) : DataEvent()
    data class OnLoadArticlesByTitleSucceed(val filterArticles: List<ArticleModel>) : DataEvent()




}