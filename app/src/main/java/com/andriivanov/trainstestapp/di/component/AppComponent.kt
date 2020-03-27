package com.andriivanov.trainstestapp.di.component

import com.andriivanov.trainstestapp.TrainTestApp
import com.andriivanov.trainstestapp.di.module.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component
    (
    modules = [
        (AndroidSupportInjectionModule::class),
        (UiModule::class),
        (NetworkModule::class),
        (ViewModelModule::class),
        (ProviderModule::class)
    ]
)
interface AppComponent : AndroidInjector<TrainTestApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TrainTestApp>()
}
