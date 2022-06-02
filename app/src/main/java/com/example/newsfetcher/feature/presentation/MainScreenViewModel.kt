package com.example.newsfetcher.feature.presentation

import androidx.lifecycle.viewModelScope
import com.example.newsfetcher.base.BaseViewModel
import com.example.newsfetcher.base.Event
import com.example.newsfetcher.feature.domain.ArticlesInteractor
import kotlinx.coroutines.launch

class MainScreenViewModel(private val interactor: ArticlesInteractor) : BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.LoadArticles)
    }



    override fun initialViewState() = ViewState(articles = emptyList())

    override fun reduce(event: Event, previousSTATE: ViewState): ViewState? {

        when(event) {

            is DataEvent.LoadArticles -> {

                viewModelScope.launch {
                    interactor.getArticles().fold(
                        onError = {},
                        onSuccess = {
                            processDataEvent(DataEvent.onLoadArticlesSucceed(it))
                        }
                    )
                }

                return null
            }

            is DataEvent.onLoadArticlesSucceed -> {
                return previousSTATE.copy(articles = event.articles)
            }

            else -> return null
        }

    }
}