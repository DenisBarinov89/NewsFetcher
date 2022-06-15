package com.example.newsfetcher.feature.filters

import android.os.Bundle
import android.view.View
import android.widget.TextView
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rvFilterArticles.adapter = adapter
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        openBottomSheet()
    }

    private fun render(viewState: FiltersViewState) {
        adapter.setData(viewState.articlesShown)
    }

    fun openBottomSheet() {
        val showDialogFragment = ActionBottom.newInstance(this)
        showDialogFragment.show(
            childFragmentManager, ActionBottom.TAG
        )
    }

    override fun onItemClick(item: String?, dateFrom: String?, dateTo: String?) {

        when (item) {
            SORT_POPULARITY -> {
                viewModel.processUIEvent(UIEvent.FilterSortByPopularityClicked)
            }
            SORT_TITLE_ASCENDING -> {
                viewModel.processUIEvent(UIEvent.FilterSortByTitleClicked)
            }
            SORT_DATE_ASCENDING -> {
                viewModel.processUIEvent(UIEvent.FilterSortByDateClicked)
            }
            GET_RESULT_BUTTON -> {
                viewModel.processUIEvent(
                    UIEvent.ShowResultDateFilterButtonClicked(
                        dateFrom = dateFrom.toString(), dateTo = dateTo.toString()
                    )
                )
            }
        }
    }
}