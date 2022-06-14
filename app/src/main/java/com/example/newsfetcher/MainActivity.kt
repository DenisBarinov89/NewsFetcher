package com.example.newsfetcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.newsfetcher.feature.bookmarks.ui.BookmarksFragment
import com.example.newsfetcher.feature.filters.FilterArticlesFragment
import com.example.newsfetcher.feature.mainscreen.MainScreenFragment
import com.example.newsfetcher.feature.mainscreen.MainScreenViewModel
import com.example.newsfetcher.ui.ActionBottom
import com.example.newsfetcher.ui.ActionBottomDialogFragment
import com.example.newsfetcher.ui.ItemClickListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), ItemClickListener {

    private val bottomNavigationMenu: BottomNavigationView by lazy { findViewById(R.id.bnvBar) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.beginTransaction().replace(R.id.container, MainScreenFragment())
            .commit()

        bottomNavigationMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemMain -> {
                    if (bottomNavigationMenu.selectedItemId != it.itemId) {
                        selectTab(MainScreenFragment())
                    }
                }
                R.id.itemBookbarks -> {
                    if (bottomNavigationMenu.selectedItemId != it.itemId) {
                        selectTab(BookmarksFragment())
                    }
                }
                R.id.itemFilter -> {
                    openBottomSheet()
                }
                else -> {}
            }
            true
        }
    }

    private fun openBottomSheet() {
        val showDialogFragment = ActionBottom.newInstance()
        showDialogFragment.show(
            supportFragmentManager, ActionBottom.TAG
        )
    }

    private fun selectTab(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
            .commit()
    }

    override fun onItemClick(item: String?, dateFrom: String?, dateTo: String?) {

        when (item) {
            SORT_POPULARITY -> {
                selectTab(FilterArticlesFragment.getNewInstance(SORT_POPULARITY, null,null))
            }
            SORT_TITLE_ASCENDING -> {
                selectTab(FilterArticlesFragment.getNewInstance(SORT_TITLE_ASCENDING, null, null))
            }
            SORT_DATE_ASCENDING -> {
                selectTab(FilterArticlesFragment.getNewInstance(SORT_DATE_ASCENDING, null, null))
            }
            GET_RESULT_BUTTON -> {
                selectTab(FilterArticlesFragment.getNewInstance(GET_RESULT_BUTTON, dateFrom, dateTo))
            }


        }

    }
}
