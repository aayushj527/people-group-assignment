package com.peoplegroup.assignmentapp

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.peoplegroup.assignmentapp.utilities.ConnectionState
import com.peoplegroup.assignmentapp.utilities.getCurrentConnectivityState
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

        val connectivityState: MutableLiveData<ConnectionState> by lazy {
            MutableLiveData<ConnectionState>(getCurrentConnectivityState())
        }
    }
}