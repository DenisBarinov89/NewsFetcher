package com.example.newsfetcher.feature.domain

import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageView
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleModel(
    val author: String?,
    val title: String?,
    val description:String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
    var bookmarkAddedFlag: Boolean = false
) : Parcelable {
}