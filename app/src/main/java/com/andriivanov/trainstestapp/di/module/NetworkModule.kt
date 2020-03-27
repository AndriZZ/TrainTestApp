package com.andriivanov.trainstestapp.di.module

import com.andriivanov.trainstestapp.BASE_URL
import com.andriivanov.trainstestapp.TrainServices
import com.andriivanov.trainstestapp.TrainTestApp
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.Executors
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(JacksonConverterFactory.create(XmlMapper()))
            .callbackExecutor(Executors.newCachedThreadPool())
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpCache(application: TrainTestApp): Cache {
        return Cache(application.cacheDir, 10 * 1024 * 1024)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .build()
    }

    @Provides
    fun provideDogServices(retrofit: Retrofit): TrainServices {
        return retrofit.create(TrainServices::class.java)
    }

}