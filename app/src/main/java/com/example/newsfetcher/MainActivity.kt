package com.example.newsfetcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.newsfetcher.feature.bookmarks.ui.BookmarksFragment
import com.example.newsfetcher.feature.filters.FilterArticlesFragment
import com.example.newsfetcher.feature.mainscreen.MainScreenFragment
import com.example.newsfetcher.feature.mainscreen.MainScreenViewModel
import com.example.newsfetcher.ui.ActionBottom
import com.example.newsfetcher.ui.ActionBottomDialogFragment
import com.example.newsfetcher.ui.ItemClickListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.BuildConfig
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val bottomNavigationMenu: BottomNavigationView by lazy { findViewById(R.id.bnvBar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container, MainScreenFragment())
            .commit()


        if (BuildConfig.DEBUG) {
            Log.d("TAG", "Debug")
        }

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
                R.id.itemSearch -> {
                    if (bottomNavigationMenu.selectedItemId != it.itemId) {
                        selectTab(FilterArticlesFragment())
                    }
                }
                else -> {}
            }
            true
        }
    }

    private fun selectTab(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
            .commit()
    }
}

