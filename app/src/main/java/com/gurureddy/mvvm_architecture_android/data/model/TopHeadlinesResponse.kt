package com.gurureddy.mvvm_architecture_android.data.model

import com.gurureddy.mvvm_architecture_android.data.model.Article

data class TopHeadlinesResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)