package com.example.newsfetcher.feature.data

import com.example.newsfetcher.feature.data.ArticlesRepository
import com.example.newsfetcher.feature.domain.ArticleModel

class ArticlesRepositoryImpl(private val source: ArticlesRemoteSource) : ArticlesRepository {
    override suspend fun getArticles(): List<ArticleModel> {
            return source.getArticles().articleList.map {
                it.toDomain()
            }
    }

    override suspend fun getArticlesSortedByPopularity(): List<ArticleModel> {
        return source.getArticlesSortedByPopularity().articleList.map {
            it.toDomain()
        }
    }
}