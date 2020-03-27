package com.andriivanov.trainstestapp.di.module

import android.content.Context
import com.andriivanov.trainstestapp.TrainServices
import com.andriivanov.trainstestapp.TrainTestApp
import com.andriivanov.trainstestapp.repository.TrainRepository
import com.andriivanov.trainstestapp.ui.MainFragment
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides


@Module
class ProviderModule {
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .serializeNulls()
            .create()
    }

    @Provides
    fun provideJackson(): JacksonXmlModule {
        return JacksonXmlModule()
    }


    @Provides
    fun provideContext(app: TrainTestApp): Context = app.applicationContext

    @Provides
    fun provideTrainRepository(tranServices: TrainServices, context: Context): TrainRepository {
        return TrainRepository(
            tranServices, context
        )
    }


    @Provides
    fun provideMainFragment(): MainFragment {
        return MainFragment()
    }


}

