package com.peoplegroup.assignmentapp.di

import com.google.gson.GsonBuilder
import com.peoplegroup.assignmentapp.BuildConfig
import com.peoplegroup.assignmentapp.data.remote.PersonService
import com.peoplegroup.assignmentapp.utilities.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    companion object {
        const val CONNECTION_TIMEOUT = 30L
    }

    @Provides
    @Singleton
    fun providePersonService(httpLoggingInterceptor: HttpLoggingInterceptor): PersonService {
        val retrofit = Retrofit
            .Builder()
            .client(
                OkHttpClient
                    .Builder()
                    .addNetworkInterceptor(interceptor = httpLoggingInterceptor)
                    .addInterceptor(NetworkConnectionInterceptor())
                    .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl(BuildConfig.API_BASE_URL)
            .build()

        return retrofit.create(PersonService::class.java)
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}