package com.example.newsfetcher.feature.bookmarks.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.domain.ArticleModel
import kotlinx.android.synthetic.main.item_article.view.*

class BookmarksArticlesAdapter(private val onItemClicked: (Int) -> Unit) : RecyclerView.Adapter<BookmarksArticlesAdapter.ViewHolder>() {

    private var articlesData: List<ArticleModel> = emptyList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView
        val textDate: TextView
        private val ivDeleteArticleFromBookmarks: ImageView

        init {
            tvTitle = view.findViewById(R.id.tvTitle)
            textDate = view.findViewById(R.id.tvDate)
            ivDeleteArticleFromBookmarks = view.findViewById<ImageView?>(R.id.ivDeleteFromBookmarks).also { it.visibility = ImageView.VISIBLE }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_article, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.ivDeleteFromBookmarks.setOnClickListener {
            onItemClicked.invoke(position)
        }
        viewHolder.tvTitle.text = articlesData[position].title
        viewHolder.textDate.text = articlesData[position].publishedAt
    }

    override fun getItemCount() = articlesData.size

    fun setData(articles : List<ArticleModel>) {
        articlesData = articles
        notifyDataSetChanged()
    }

}