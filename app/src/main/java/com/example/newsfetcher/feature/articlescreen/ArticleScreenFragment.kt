package com.example.newsfetcher.feature.articlescreen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.newsfetcher.BUNDLE_KEY_FOR_ARTICLE_MODEL
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.domain.ArticleModel
import com.example.newsfetcher.feature.mainscreen.MainScreenFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.Executors

class ArticleScreenFragment : Fragment(R.layout.fragment_article_screen) {

    private val viewModel: ArticleScreenViewModel by viewModel()
    private val articleImage: ImageView by lazy { requireActivity().findViewById(R.id.ivArticle) }
    private val articleTitle: TextView by lazy { requireActivity().findViewById(R.id.tvArticleTitle) }
    private val articleContent: TextView by lazy { requireActivity().findViewById(R.id.tvArticleContent) }
    private val articleDescription: TextView by lazy { requireActivity().findViewById(R.id.tvArticleDescription) }
    private val articleLink: TextView by lazy { requireActivity().findViewById(R.id.tvArticleLink) }
    private val backFromArticleScreenButton: ImageView by lazy { requireActivity().findViewById(R.id.btnBackFromArticleScreen) }

    companion object {
        fun getNewInstance(args: Bundle?) : ArticleScreenFragment {
            val articleScreenFragment = ArticleScreenFragment()
            articleScreenFragment.arguments = args
            return articleScreenFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        val currentArticle : ArticleModel = this.arguments?.get(BUNDLE_KEY_FOR_ARTICLE_MODEL) as ArticleModel
        viewModel.processUIEvent(DataEvent.ShowArticle(currentArticle))

        backFromArticleScreenButton.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
        }


    }


    private fun render(viewState: ViewState) {
        articleTitle.text = viewState.articleTitle
        articleDescription.text = viewState.articleDescription
        articleContent.text = viewState.articleContent
        articleLink.text = viewState.articleLink
        viewState.articleUrlToImage?.let { getImageOfArticleFromUrl(it) }
    }


    private fun getImageOfArticleFromUrl(urlToImage: String) {
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        var image: Bitmap?
        executor.execute {
            try {
                val `in` = java.net.URL(urlToImage).openStream()
                image = BitmapFactory.decodeStream(`in`)
                handler.post {
                    articleImage.setImageBitmap(image)
                }
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}