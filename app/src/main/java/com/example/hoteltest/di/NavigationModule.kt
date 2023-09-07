package com.example.hoteltest.di

import com.example.hoteltest.navigation.NavigationHolder
import com.example.hoteltest.navigation.NavigatorImp
import com.example.hoteltest.navigation.NavigatorInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {
    @Singleton
    @Provides
    fun provideNavigationHolder():NavigationHolder = NavigationHolder()

    fun provideNavigationInterface(navigationHolder: NavigationHolder):NavigatorInterface = NavigatorImp()
}