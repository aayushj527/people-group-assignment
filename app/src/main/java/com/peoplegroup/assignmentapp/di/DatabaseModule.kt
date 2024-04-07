package com.peoplegroup.assignmentapp.di

import androidx.room.Room
import com.peoplegroup.assignmentapp.AppClass
import com.peoplegroup.assignmentapp.data.database.DataBase
import com.peoplegroup.assignmentapp.data.database.PersonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    companion object {
        const val DATABASE_NAME = "person_database"
    }

    @Singleton
    @Provides
    fun provideDatabase(): DataBase {
        return Room
            .databaseBuilder(
                AppClass.getContext(),
                DataBase::class.java,
                DATABASE_NAME
            ).build()
    }

    @Singleton
    @Provides
    fun providePersonDao(database: DataBase): PersonDao {
        return database.personDao()
    }
}