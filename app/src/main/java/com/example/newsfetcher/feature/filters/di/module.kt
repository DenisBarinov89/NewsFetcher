package com.example.newsfetcher.feature.filters.di

import com.example.newsfetcher.feature.filters.FilterArticlesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val filterModule = module {

    viewModel { FilterArticlesViewModel(interactor = get(), bookmarksInteractor = get()) }

}