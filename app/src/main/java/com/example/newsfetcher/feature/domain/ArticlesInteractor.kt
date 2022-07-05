package com.example.newsfetcher.feature.domain

import com.example.newsfetcher.base.Either
import com.example.newsfetcher.base.attempt
import com.example.newsfetcher.feature.data.ArticlesRepository

class ArticlesInteractor(private val repository: ArticlesRepository) {

    suspend fun getArticles() = attempt { repository.getArticles() }

    suspend fun getArticlesSortBy(sortBy: String, q: String) =
        attempt { repository.getArticlesSortedBy(sortBy, q) }

    suspend fun getArticlesFilterByDate(dateFrom: String, dateTo: String, q: String) =
        attempt {
            repository.getArticlesFilterByDate(
                dateFrom = dateFrom,
                dateTo = dateTo,
                q = q
            )
        }
}

