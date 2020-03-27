package com.andriivanov.trainstestapp.di.module

import com.andriivanov.trainstestapp.ui.MainActivity
import com.andriivanov.trainstestapp.ui.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class UiModule {


    @ContributesAndroidInjector
    abstract fun bindMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}