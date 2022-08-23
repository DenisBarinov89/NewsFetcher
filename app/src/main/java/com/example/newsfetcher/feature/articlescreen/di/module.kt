package com.example.newsfetcher.feature.articlescreen.di

import android.os.Bundle
import com.example.newsfetcher.feature.articlescreen.ArticleScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val articleScreenModule = module {

    viewModel { ArticleScreenViewModel() }

}