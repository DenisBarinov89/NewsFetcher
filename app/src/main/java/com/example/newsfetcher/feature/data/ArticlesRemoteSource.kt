package com.example.newsfetcher.feature.data

import com.example.newsfetcher.feature.data.model.ArticlesRemoteModel

class ArticlesRemoteSource(private val api: NewsApi) {

    suspend fun getArticles() : ArticlesRemoteModel {
        return api.getArticles()
    }

    suspend fun getArticlesSortedByPopularity() : ArticlesRemoteModel {
        return api.getArticlesSortedByPopularity()
    }

}