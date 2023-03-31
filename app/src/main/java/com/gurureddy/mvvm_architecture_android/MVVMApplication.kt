package com.gurureddy.mvvm_architecture_android

import android.app.Application
import com.gurureddy.mvvm_architecture_android.di.component.ApplicationComponent
import com.gurureddy.mvvm_architecture_android.di.component.DaggerApplicationComponent
import com.gurureddy.mvvm_architecture_android.di.module.ApplicationModule

class MVVMApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}