package com.example.newsfetcher.feature.articlescreen


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.example.newsfetcher.base.BaseViewModel
import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.domain.ArticleModel
import java.util.concurrent.Executors


class ArticleScreenViewModel() : BaseViewModel<ViewState>() {


    override fun initialViewState(): ViewState = ViewState(
        articleContent = "",
        articleDescription = "",
        articleUrlToImage = "",
        articleLink = "",
        articleTitle = ""
    )

    override fun reduce(event: Event, previousState: ViewState): ViewState? {

        when (event) {
            is DataEvent.ShowArticle -> {
                return previousState.copy(
                    articleTitle = event.currentArticle.title,
                    articleLink = event.currentArticle.url,
                    articleUrlToImage = event.currentArticle.urlToImage,
                    articleDescription = event.currentArticle.description,
                    articleContent = event.currentArticle.content?.replace(Regex("\\[.*"),"read full here:")
                )
            }
        }
        return null
    }
}


