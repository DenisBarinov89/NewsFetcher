package com.example.newsfetcher.feature.domain

import com.example.newsfetcher.base.Either
import com.example.newsfetcher.base.attempt
import com.example.newsfetcher.feature.data.ArticlesRepository

class ArticlesInteractor(private val repository: ArticlesRepository) {

    //еще раз объяснить, что такое attempt
    suspend fun getArticles() = attempt { repository.getArticles() }

    suspend fun getArticlesSortBy(sortBy: String) =
        attempt { repository.getArticlesSortedBy(sortBy) }

    suspend fun getArticlesFilterByDate(dateFrom: String, dateTo: String) =
        attempt {
            repository.getArticlesFilterByDate(
                dateFrom = dateFrom,
                dateTo = dateTo
            )
        }
}

