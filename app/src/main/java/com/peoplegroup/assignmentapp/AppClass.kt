package com.peoplegroup.assignmentapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppClass : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: AppClass? = null

        fun getContext(): Context {
            return instance!!.applicationContext
        }
    }
}