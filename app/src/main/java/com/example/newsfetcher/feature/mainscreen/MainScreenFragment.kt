package com.example.newsfetcher.feature.mainscreen

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfetcher.R
import com.example.newsfetcher.base.openArticle

import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    private val viewModel: MainScreenViewModel by viewModel()
    private val rvArticles: RecyclerView by lazy { requireActivity().findViewById(R.id.rvArticles) }
    private val adapter: ArticlesAdapter by lazy {
        ArticlesAdapter({ index ->
            viewModel.processUIEvent(UIEvent.OnArticleClicked(index))
        }, {currentArticle -> openArticle(currentArticle)}, this@MainScreenFragment)
    }
    private val icErrorOccurred: ImageView by lazy { requireActivity().findViewById(R.id.ivErrorOccurred) }
    private val tvErrorOccurred: TextView by lazy { requireActivity().findViewById(R.id.tvErrorOccurredNotification) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        rvArticles.adapter = adapter
    }

    private fun render(viewState: ViewState) {
        adapter.setData(viewState.articlesShown)
        icErrorOccurred.isVisible = viewState.isErrorOccurred
        tvErrorOccurred.isVisible = viewState.isErrorOccurred
    }
}

