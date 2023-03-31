package com.gurureddy.mvvm_architecture_android.di.component

import com.gurureddy.mvvm_architecture_android.ui.MainActivity
import com.gurureddy.mvvm_architecture_android.di.ActivityScope
import com.gurureddy.mvvm_architecture_android.di.module.ActivityModule
import dagger.Component


@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: MainActivity)
}