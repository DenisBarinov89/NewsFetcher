package com.example.newsfetcher.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.newsfetcher.BUNDLE_KEY_FOR_ARTICLE_MODEL
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.articlescreen.ArticleScreenFragment
import com.example.newsfetcher.feature.domain.ArticleModel

inline fun <reified T> attempt(func: () -> T): Either<Throwable, T> = try {
    Either.Right(func.invoke())
} catch (e : Throwable) {
    Either.Left(e)
}

fun Fragment.openArticle(currentArticle: ArticleModel) {
    val bundle = Bundle()
    bundle.putParcelable(BUNDLE_KEY_FOR_ARTICLE_MODEL, currentArticle)
    parentFragmentManager.beginTransaction().add(R.id.container, ArticleScreenFragment.getNewInstance(bundle)).commit()
}