package com.example.newsfetcher.feature.data

import com.example.newsfetcher.feature.domain.ArticleModel

interface ArticlesRepository {

    suspend fun getArticles() : List<ArticleModel>

    suspend fun getArticlesSortedByPopularity() : List<ArticleModel>

}