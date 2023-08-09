package com.furkan.mvvm_news_app.di

import com.furkan.mvvm_news_app.BuildConfig
import com.furkan.mvvm_news_app.data.remote.NewsApi
import com.furkan.mvvm_news_app.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Timber.d(message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }



    @Singleton
    @Provides
    fun provideNewsApi(
        okHttpClient: OkHttpClient
    ): NewsApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
            .create(NewsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsRepository(
        api: NewsApi
    ) = NewsRepository(api)


}