package com.example.newsfetcher

import android.app.Application
import com.example.newsfetcher.di.dataBaseModule
import com.example.newsfetcher.di.networkModule
import com.example.newsfetcher.feature.bookmarks.di.bookmarksModule
import com.example.newsfetcher.feature.di.mainScreenModule
import com.example.newsfetcher.feature.filters.di.filterModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(networkModule, mainScreenModule, bookmarksModule, dataBaseModule, filterModule)
        }
    }
}