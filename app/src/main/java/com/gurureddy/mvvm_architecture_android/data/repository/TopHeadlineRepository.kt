package com.gurureddy.mvvm_architecture_android.data.repository

import com.gurureddy.mvvm_architecture_android.data.api.NetworkService
import com.gurureddy.mvvm_architecture_android.data.model.Article
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


class TopHeadlineRepository @Inject constructor(private val networkService: NetworkService) {
    fun getTopHeadline(country: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map { topHeadlineResponse ->
            topHeadlineResponse.articles
        }
    }
}
