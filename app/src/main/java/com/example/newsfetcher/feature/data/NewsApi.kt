package com.example.newsfetcher.feature.data

import com.example.newsfetcher.API_KEY
import com.example.newsfetcher.feature.data.model.ArticlesRemoteModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getArticles(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = API_KEY
    ) : ArticlesRemoteModel

    @GET("v2/everything")
    suspend fun getArticlesSortedBy(
        @Query("language") country: String = "en",
        @Query("sortBy") sortBy: String,
        @Query("q") q: String,
        @Query("apiKey") apiKey: String = API_KEY
    ) : ArticlesRemoteModel

    @GET("v2/everything")
    suspend fun getArticlesFilterByDate(
        @Query("q") q: String,
        @Query("from") dateFrom: String,
        @Query("to") dateTo: String,
        @Query("language") country: String = "en",
        @Query("apiKey") apiKey: String = API_KEY
    ) : ArticlesRemoteModel



}
