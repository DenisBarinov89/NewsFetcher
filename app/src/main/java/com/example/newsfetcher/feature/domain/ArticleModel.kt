package com.example.newsfetcher.feature.domain

import android.widget.ImageView

data class ArticleModel(
    val author: String,
    val title: String,
    val description:String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    var bookmarkAddedFlag: Boolean = false

) {
}