package com.example.newsfetcher.feature.bookmarks.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.mainscreen.ArticlesAdapter
import com.example.newsfetcher.feature.mainscreen.UIEvent
import com.example.newsfetcher.feature.mainscreen.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {

    private val viewModel: BookmarksScreenViewModel by viewModel()
    private val rvArticles: RecyclerView by lazy { requireActivity().findViewById(R.id.rvBookmarkedArticles) }
    private val adapter: BookmarksArticlesAdapter by lazy { BookmarksArticlesAdapter{ index ->
        viewModel.processUIEvent(UiEvent.OnBookmarkedArticleClicked(index))
    } }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        rvArticles.adapter = adapter

    }

    private fun render(viewState: com.example.newsfetcher.feature.bookmarks.ui.ViewState) {
        adapter.setData(viewState.bookmarksArticle)
    }


}


