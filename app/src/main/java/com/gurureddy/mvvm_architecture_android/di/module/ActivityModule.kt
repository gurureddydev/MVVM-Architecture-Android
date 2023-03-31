package com.gurureddy.mvvm_architecture_android.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gurureddy.mvvm_architecture_android.data.repository.TopHeadlineRepository
import com.gurureddy.mvvm_architecture_android.di.ActivityContext
import com.gurureddy.mvvm_architecture_android.ui.TopHeadlineAdapter
import com.gurureddy.mvvm_architecture_android.ui.TopHeadlineViewModel
import com.gurureddy.mvvm_architecture_android.ui.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity:AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext():Context{
        return activity
    }

    @Provides
    fun provideTopHeadlineViewModel(topHeadlineRepository: TopHeadlineRepository): TopHeadlineViewModel {
        return ViewModelProvider(activity, ViewModelProviderFactory(TopHeadlineViewModel::class){
            TopHeadlineViewModel(topHeadlineRepository)
        })[TopHeadlineViewModel::class.java]
    }

    @Provides
    fun provideTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())
}