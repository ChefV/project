package com.example.news.presentation.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.data.model.NewsData

class NewsAdapter(
    private val newsData: NewsData, private val navController: NavController
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsImageView: ImageView = itemView.findViewById(R.id.newsImageView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        val newsLayout: LinearLayout = itemView.findViewById(R.id.newsLayout)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val data = newsData.data?.get(position)

        holder.newsLayout.setOnClickListener {
            navController.navigate(NewsFragmentDirections.actionNewsFragmentToArticleFragment(data!!))
        }

        holder.titleTextView.text = data?.title
        holder.authorTextView.text = data?.author
        holder.dateTextView.text = data?.date
        holder.timeTextView.text = data?.time

        Glide.with(holder.newsImageView.context).load(data?.imageUrl)
            .into(holder.newsImageView)
    }

    override fun getItemCount(): Int {
        return newsData.data?.size ?: 0
    }
}
