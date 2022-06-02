package com.example.newsfetcher.feature.domain

data class ArticleModel(
    val author: String,
    val title: String,
    val description:String,
    val url: String
) {
}