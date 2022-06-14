package com.example.newsfetcher.feature.data

import com.example.newsfetcher.feature.data.ArticlesRepository
import com.example.newsfetcher.feature.domain.ArticleModel

class ArticlesRepositoryImpl(private val source: ArticlesRemoteSource) : ArticlesRepository {
    override suspend fun getArticles(): List<ArticleModel> {
            return source.getArticles().articleList.map {
                it.toDomain()
            }
    }

    override suspend fun getArticlesSortedBy(sortBy: String): List<ArticleModel> {
        return source.getArticlesSortedBy(sortBy = sortBy).articleList.map {
            it.toDomain()
        }
    }

    override suspend fun getArticlesFilterByDate(dateFrom: String, dateTo: String): List<ArticleModel> {
        return source.getArticlesFilterByDate(dateFrom = dateFrom, dateTo = dateTo).articleList.map {
            it.toDomain()
        }
    }


}