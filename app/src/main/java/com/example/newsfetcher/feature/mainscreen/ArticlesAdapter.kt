package com.example.newsfetcher.feature.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.domain.ArticleModel
import kotlinx.android.synthetic.main.item_article.view.*

class ArticlesAdapter(private val onAddToBookmarksClicked: (Int) -> Unit, private val onArticleClicked: (ArticleModel) -> Unit) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    private var articlesData: List<ArticleModel> = emptyList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView
        val textDate: TextView
        val ivBookmarkAdded: ImageView
        private val ivAddToBookmarks: ImageView

        init {
            tvTitle = view.findViewById(R.id.tvTitle)
            textDate = view.findViewById(R.id.tvDate)
            ivBookmarkAdded = view.findViewById(R.id.ivBookmarkAdded)
            ivAddToBookmarks = view.findViewById<ImageView?>(R.id.ivAddToBookmarks).also { it.visibility = ImageView.VISIBLE }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_article, viewGroup, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.ivAddToBookmarks.setOnClickListener {
            onAddToBookmarksClicked.invoke(position)
            notifyDataSetChanged()
        }

        viewHolder.itemView.setOnClickListener{
            onArticleClicked.invoke(articlesData[position])
        }

        //нужно разобраться, как все-таки сделать корректное изменение отображения иконок через ViewModel и функцию render
        viewHolder.ivBookmarkAdded.visibility = if (articlesData[position].bookmarkAddedFlag) ImageView.VISIBLE else ImageView.GONE
        viewHolder.tvTitle.text = articlesData[position].title
        viewHolder.textDate.text = articlesData[position].publishedAt

    }

    override fun getItemCount() = articlesData.size

    fun setData(articles : List<ArticleModel>) {
        articlesData = articles
        notifyDataSetChanged()
    }

}