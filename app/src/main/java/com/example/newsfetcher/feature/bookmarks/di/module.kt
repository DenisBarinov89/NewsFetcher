package com.example.newsfetcher.feature.bookmarks.di

import com.example.newsfetcher.feature.bookmarks.data.local.BookmarksLocalSource
import com.example.newsfetcher.feature.bookmarks.data.local.BookmarksRepository
import com.example.newsfetcher.feature.bookmarks.data.local.BookmarksRepositoryImpl
import com.example.newsfetcher.feature.bookmarks.domain.BookmarksInteractor
import com.example.newsfetcher.feature.bookmarks.ui.BookmarksScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val BOOKMARKS_TABLE = "BOOKMARKS_TABLE"

val bookmarksModule = module {

    single { BookmarksLocalSource(get()) }

    single { BookmarksInteractor(get()) }

    single<BookmarksRepository> { BookmarksRepositoryImpl(get()) }

    viewModel { BookmarksScreenViewModel(get()) }

}