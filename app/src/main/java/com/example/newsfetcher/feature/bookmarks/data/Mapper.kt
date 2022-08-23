package com.example.newsfetcher.feature.bookmarks.data

import com.example.newsfetcher.feature.bookmarks.data.local.model.BookmarkEntity
import com.example.newsfetcher.feature.domain.ArticleModel

fun BookmarkEntity.toDomain() = ArticleModel(

    title = title,
    author = author ?: "",
    description = description ?: "",
    url = url,
    urlToImage = urlToImage ?: "",
    publishedAt = publishedAt,
    content = content
)

fun ArticleModel.toEntity() = BookmarkEntity(
    title = title ?: "",
    author = author,
    description = description,
    url = url ?: "",
    urlToImage = urlToImage,
    publishedAt = publishedAt ?: "",
    content = content ?: ""
)