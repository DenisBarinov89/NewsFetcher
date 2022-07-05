package com.example.newsfetcher.feature.filters

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfetcher.*
import com.example.newsfetcher.base.openArticle
import com.example.newsfetcher.feature.mainscreen.MainScreenFragment
import com.example.newsfetcher.feature.mainscreen.ViewState
import com.example.newsfetcher.ui.ActionBottom
import com.example.newsfetcher.ui.ItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.w3c.dom.Text

class FilterArticlesFragment : Fragment(R.layout.fragment_filter_screen), ItemClickListener {

    private val viewModel: FilterArticlesViewModel by viewModel()
    private val rvFilterArticles: RecyclerView by lazy { requireActivity().findViewById(R.id.rvFilterArticles) }
    private val adapter: FilterArticlesAdapter by lazy {
        FilterArticlesAdapter({ index ->
            viewModel.processUIEvent(UIEvent.OnAddToBookmarksClicked(index))},
            {currentArticle -> openArticle(currentArticle)},
            this@FilterArticlesFragment)
    }
    private val icFilters: ImageView by lazy { requireActivity().findViewById(R.id.ivFilters) }
    private val btnFindArticles: Button by lazy { requireActivity().findViewById(R.id.btnFindNews) }
    private val etFindArticlesRequest: EditText by lazy { requireActivity().findViewById(R.id.etSearchRequest) }
    private val tvNoSearchResults: TextView by lazy { requireActivity().findViewById(R.id.tvNoSearchResultNotification) }
    private val icNoSearchResults: ImageView by lazy { requireActivity().findViewById(R.id.ivNoSearchResultNotification) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvFilterArticles.adapter = adapter
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        icFilters.setOnClickListener {
            openBottomSheet()
        }
        btnFindArticles.setOnClickListener {
            viewModel.processUIEvent(
                UIEvent.OnFindArticlesButtonClicked(
                    q = getSearchUserRequest()
                )
            )
        }
    }

    private fun render(viewState: FiltersViewState) {
        icFilters.isVisible = viewState.isFiltersEnabled
        adapter.setData(viewState.articlesShown)
        tvNoSearchResults.isVisible = viewState.isSearchResultsEmpty
        icNoSearchResults.isVisible = viewState.isSearchResultsEmpty
    }

    private fun openBottomSheet() {
        val showDialogFragment = ActionBottom.newInstance(this)
        showDialogFragment.show(
            childFragmentManager, ActionBottom.TAG
        )
    }

    private fun getSearchUserRequest(): String {
        val defaultSearchRequest = "all"
        return if (etFindArticlesRequest.editableText.isEmpty()) defaultSearchRequest else etFindArticlesRequest.editableText.toString()
    }

    override fun onItemClick(item: String?, dateFrom: String?, dateTo: String?) {
        when (item) {
            SORT_POPULARITY -> {
                viewModel.processUIEvent(
                    UIEvent.FilterSortByPopularityClicked(
                        q = getSearchUserRequest()
                    )
                )
            }
            SORT_TITLE_ASCENDING -> {
                viewModel.processUIEvent(
                    UIEvent.FilterSortByTitleClicked(
                        q = getSearchUserRequest()
                    )
                )
            }
            SORT_DATE_ASCENDING -> {
                viewModel.processUIEvent(
                    UIEvent.FilterSortByDateClicked(
                        q = getSearchUserRequest()
                    )
                )
            }
            GET_RESULT_BUTTON -> {
                viewModel.processUIEvent(
                    UIEvent.ShowResultDateFilterButtonClicked(
                        dateFrom = dateFrom.toString(), dateTo = dateTo.toString(),
                        q = getSearchUserRequest()
                    )
                )
            }
        }
    }
}