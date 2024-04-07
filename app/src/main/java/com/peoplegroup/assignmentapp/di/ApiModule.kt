package com.peoplegroup.assignmentapp.di

import com.google.gson.GsonBuilder
import com.peoplegroup.assignmentapp.BuildConfig
import com.peoplegroup.assignmentapp.data.api.PersonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {
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
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl(BuildConfig.API_BASE_URL)
            .build()

        return retrofit.create(PersonService::class.java)
    }
}