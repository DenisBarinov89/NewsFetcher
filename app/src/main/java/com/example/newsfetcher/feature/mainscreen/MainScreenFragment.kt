package com.example.newsfetcher.feature.mainscreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfetcher.BUNDLE_KEY_FOR_ARTICLE_MODEL
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.articlescreen.ArticleScreenFragment
import com.example.newsfetcher.feature.domain.ArticleModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    private val viewModel: MainScreenViewModel by viewModel()
    private val rvArticles: RecyclerView by lazy { requireActivity().findViewById(R.id.rvArticles) }
    private val adapter: ArticlesAdapter by lazy {
        ArticlesAdapter({ index ->
            viewModel.processUIEvent(UIEvent.OnArticleClicked(index))
        }, {currentArticle -> openArticle(currentArticle)})
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        rvArticles.adapter = adapter
    }

    private fun render(viewState: ViewState) {
        adapter.setData(viewState.articlesShown)
    }

    private fun openArticle(currentArticle: ArticleModel) {
        val bundle = Bundle()
        bundle.putParcelable(BUNDLE_KEY_FOR_ARTICLE_MODEL, currentArticle)
        parentFragmentManager.beginTransaction().add(R.id.container, ArticleScreenFragment.getNewInstance(bundle)).commit()
    }

}

