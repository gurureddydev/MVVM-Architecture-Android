package com.gurureddy.mvvm_architecture_android.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gurureddy.mvvm_architecture_android.data.model.Article
import com.gurureddy.mvvm_architecture_android.databinding.TopHeadlineItemLayoutBinding

class TopHeadlineAdapter(
    private val articleList: ArrayList<Article>
) : RecyclerView.Adapter<TopHeadlineAdapter.DataViewHolder>() {
    class DataViewHolder(private val binding: TopHeadlineItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.apply {
                textViewTitle.text = article.title
                textViewDescription.text = article.description
                textViewSource.text = article.source.name
                Glide.with(binding.imageViewBanner.context)
                    .load(article.urlToImage)
                    .into(imageViewBanner)
                itemView.setOnClickListener {
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(it.context, Uri.parse(article.url))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(
        TopHeadlineItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(articleList[position])
    }

    override fun getItemCount(): Int = articleList.size

    fun addData(list: List<Article>) {
        articleList.addAll(list)
    }
}
