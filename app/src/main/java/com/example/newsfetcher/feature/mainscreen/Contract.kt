package com.example.newsfetcher.feature.mainscreen

import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.domain.ArticleModel

data class ViewState(
    val articlesShown: List<ArticleModel>,
    val articles: List<ArticleModel>,
    val isErrorOccurred: Boolean
)

sealed class UIEvent : Event {
    data class OnArticleClicked(val index: Int) : UIEvent()
}

sealed class DataEvent : Event {
    object LoadArticles : DataEvent()
    data class OnLoadArticlesSucceed(val articles: List<ArticleModel>) : DataEvent()
    object OnLoadArticlesFailed : DataEvent()

}