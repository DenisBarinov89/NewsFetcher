package com.example.newsfetcher.feature.filters

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.mainscreen.ViewState

class FilterArticlesFragment : Fragment(R.layout.fragment_main_screen) {

    private val text: TextView by lazy { requireActivity().findViewById(R.id.textView) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text.text = "3"

    }

    private fun render(viewState: FiltersViewState) {

    }


}