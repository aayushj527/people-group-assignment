package com.peoplegroup.assignmentapp

import android.app.Application
import androidx.room.Room
import com.peoplegroup.assignmentapp.data.database.DataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppClass : Application() {
    companion object {
        lateinit var database: DataBase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room
            .databaseBuilder(
                applicationContext,
                DataBase::class.java,
                "person_database"
            ).build()
    }
}