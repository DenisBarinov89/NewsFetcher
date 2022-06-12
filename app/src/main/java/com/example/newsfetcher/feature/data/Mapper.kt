package com.example.newsfetcher.feature.data

import com.example.newsfetcher.feature.data.model.ArticleRemoteModel
import com.example.newsfetcher.feature.domain.ArticleModel

//зачем нам маппер
//зачем нам фукнция расширения в маппере

fun ArticleRemoteModel.toDomain() = ArticleModel(
    title = title ?: "",
    author = author ?: "",
    description = description ?: "",
    url = url ?: "",
    urlToImage = urlToImage ?: "",
    publishedAt = publishedAt ?: ""
)