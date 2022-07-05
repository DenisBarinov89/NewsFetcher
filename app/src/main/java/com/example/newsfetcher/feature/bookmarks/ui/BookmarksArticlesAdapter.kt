package com.example.newsfetcher.feature.bookmarks.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.domain.ArticleModel
import com.example.newsfetcher.feature.mainscreen.MainScreenFragment
import kotlinx.android.synthetic.main.item_article.view.*

class BookmarksArticlesAdapter(private val onDeleteFromBookmarksClicked: (Int) -> Unit, private val onArticleClicked: (ArticleModel) -> Unit, private val context: BookmarksFragment) : RecyclerView.Adapter<BookmarksArticlesAdapter.ViewHolder>() {

    private var articlesData: List<ArticleModel> = emptyList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView
        val textDate: TextView
        val tvDescription: TextView
        val ivArticleImage: ImageView
        private val ivDeleteArticleFromBookmarks: ImageView

        init {
            tvTitle = view.findViewById(R.id.tvTitle)
            textDate = view.findViewById(R.id.tvDate)
            tvDescription = view.findViewById(R.id.tvDescription)
            ivArticleImage = view.findViewById(R.id.ivArticleFromList)
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
            onDeleteFromBookmarksClicked.invoke(position)
        }

        viewHolder.itemView.setOnClickListener {
            onArticleClicked.invoke(articlesData[position])
        }

        if (!articlesData[position].urlToImage.isNullOrEmpty()) {
            Glide.with(context)
                .load(articlesData[position].urlToImage)
                .centerCrop()
                .into(viewHolder.ivArticleImage)
        }
        viewHolder.tvTitle.text = articlesData[position].title
        viewHolder.tvDescription.text = articlesData[position].description
        viewHolder.textDate.text = articlesData[position].publishedAt
    }

    override fun getItemCount() = articlesData.size

    fun setData(articles : List<ArticleModel>) {
        articlesData = articles
        notifyDataSetChanged()
    }

}