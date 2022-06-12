package com.example.newsfetcher.feature.filters

import com.example.newsfetcher.base.BaseViewModel
import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.bookmarks.domain.BookmarksInteractor
import com.example.newsfetcher.feature.domain.ArticlesInteractor
import com.example.newsfetcher.feature.mainscreen.ViewState

class FilterArticlesViewModel(
    private val interactor: ArticlesInteractor
) : BaseViewModel<FiltersViewState>() {


    override fun initialViewState(): FiltersViewState = FiltersViewState(articlesShown = emptyList(), articles = emptyList())

    override fun reduce(event: Event, previousSTATE: FiltersViewState): FiltersViewState? {


        return null


    }


}