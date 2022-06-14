package com.example.newsfetcher.feature.filters

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfetcher.R
import com.example.newsfetcher.SORT_DATE_ASCENDING
import com.example.newsfetcher.SORT_POPULARITY
import com.example.newsfetcher.SORT_TITLE_ASCENDING
import com.example.newsfetcher.feature.mainscreen.MainScreenFragment
import com.example.newsfetcher.feature.mainscreen.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilterArticlesFragment : Fragment(R.layout.fragment_filter_screen) {


    private val viewModel: FilterArticlesViewModel by viewModel()
    private val rvFilterArticles: RecyclerView by lazy { requireActivity().findViewById(R.id.rvFilterArticles) }

    private val adapter: FilterArticlesAdapter by lazy {
        FilterArticlesAdapter()
    }

    companion object {
        fun getNewInstance(args: String) : FilterArticlesFragment {
            val bundle = Bundle()
            bundle.putString("sort", args)
            val filterArticlesFragment = FilterArticlesFragment()
            filterArticlesFragment.arguments = bundle
            return filterArticlesFragment
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvFilterArticles.adapter = adapter
        viewModel.viewState.observe(viewLifecycleOwner, ::render)

        when (arguments?.get("sort")) {
            SORT_POPULARITY -> {
                viewModel.processUIEvent(DataEvent.LoadArticlesByPopularity)
            }
            SORT_TITLE_ASCENDING -> {
                viewModel.processUIEvent(DataEvent.LoadArticlesSortingByTitle)
            }
            SORT_DATE_ASCENDING -> {
                viewModel.processUIEvent(UIEvent.OnTestPreviousViewState)
            }
        }

    }

    private fun render(viewState: FiltersViewState) {
        adapter.setData(viewState.articlesShown)

    }


}