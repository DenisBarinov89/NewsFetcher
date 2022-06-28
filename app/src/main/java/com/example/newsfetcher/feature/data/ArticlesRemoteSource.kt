package com.example.newsfetcher.feature.data

import com.example.newsfetcher.feature.data.model.ArticlesRemoteModel

class ArticlesRemoteSource(private val api: NewsApi) {

    suspend fun getArticles() : ArticlesRemoteModel {
        return api.getArticles()
    }

    suspend fun getArticlesSortedBy(sortBy: String, q: String) : ArticlesRemoteModel {
        return api.getArticlesSortedBy(sortBy = sortBy, q = q)
    }

    suspend fun getArticlesFilterByDate(dateFrom: String, dateTo: String, q: String) : ArticlesRemoteModel {
        return api.getArticlesFilterByDate(dateFrom = dateFrom, dateTo = dateTo, q = q)
    }

}