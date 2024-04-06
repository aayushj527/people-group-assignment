package com.peoplegroup.assignmentapp.di

import com.peoplegroup.assignmentapp.AppClass
import com.peoplegroup.assignmentapp.BuildConfig
import com.peoplegroup.assignmentapp.data.api.PersonService
import com.peoplegroup.assignmentapp.data.database.PersonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class Module {
    companion object {
        const val CONNECTION_TIMEOUT = 30L
    }

    @Provides
    @Singleton
    fun providePersonService(): PersonService {
        val retrofit = Retrofit
            .Builder()
            .client(
                OkHttpClient
                    .Builder()
                    .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .build()
            )
            .baseUrl(BuildConfig.API_BASE_URL)
            .build()

        return retrofit.create(PersonService::class.java)
    }

    @Singleton
    @Provides
    fun providePersonDao(): PersonDao {
        return AppClass.database.personDao()
    }
}