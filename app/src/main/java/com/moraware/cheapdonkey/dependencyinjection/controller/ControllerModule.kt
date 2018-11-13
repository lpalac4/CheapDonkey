package com.moraware.cheapdonkey.dependencyinjection.controller

import android.content.Context
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides

@Module
class ControllerModule(private val mActivity: AppCompatActivity) {

    @Provides
    fun providesContext(): Context {
        return mActivity.applicationContext
    }

    @Provides
    fun providesActivity(): AppCompatActivity {
        return mActivity
    }
}