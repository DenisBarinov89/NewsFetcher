package com.example.newsfetcher.feature.filters

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfetcher.*
import com.example.newsfetcher.feature.mainscreen.MainScreenFragment
import com.example.newsfetcher.feature.mainscreen.ViewState
import com.example.newsfetcher.ui.ActionBottom
import com.example.newsfetcher.ui.ItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilterArticlesFragment : Fragment(R.layout.fragment_filter_screen), ItemClickListener {

    private val viewModel: FilterArticlesViewModel by viewModel()
    private val rvFilterArticles: RecyclerView by lazy { requireActivity().findViewById(R.id.rvFilterArticles) }
    private val adapter: FilterArticlesAdapter by lazy { FilterArticlesAdapter() }
    private val icFilters: ImageView by lazy { requireActivity().findViewById(R.id.ivFilters) }
    private val btnfindArticles: Button by lazy { requireActivity().findViewById(R.id.btnFindNews) }
    private val etFindArticlesRequest: EditText by lazy { requireActivity().findViewById(R.id.etSearchRequest) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvFilterArticles.adapter = adapter
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        icFilters.setOnClickListener {
            openBottomSheet()
        }
        btnfindArticles.setOnClickListener {
            viewModel.processUIEvent(UIEvent.OnFindArticlesButtonClicked(
                q = getSearchUserRequest()))
        }
    }

    private fun render(viewState: FiltersViewState) {
        icFilters.isVisible = viewState.isFiltersEnabled
        adapter.setData(viewState.articlesShown)
    }

    private fun openBottomSheet() {
        val showDialogFragment = ActionBottom.newInstance(this)
        showDialogFragment.show(
            childFragmentManager, ActionBottom.TAG
        )
    }

    private fun getSearchUserRequest() : String {
        val defaultSearchRequest = "all"
        return if (etFindArticlesRequest.editableText.isEmpty()) defaultSearchRequest else etFindArticlesRequest.editableText.toString()
    }

    override fun onItemClick(item: String?, dateFrom: String?, dateTo: String?) {
        when (item) {
            SORT_POPULARITY -> {
                viewModel.processUIEvent(UIEvent.FilterSortByPopularityClicked(
                    q = getSearchUserRequest()))
            }
            SORT_TITLE_ASCENDING -> {
                viewModel.processUIEvent(UIEvent.FilterSortByTitleClicked(
                    q = getSearchUserRequest()))
            }
            SORT_DATE_ASCENDING -> {
                viewModel.processUIEvent(UIEvent.FilterSortByDateClicked(
                    q = getSearchUserRequest()))
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