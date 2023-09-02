package com.example.hoteltest.di

import com.example.hoteltest.domain.interfaces.api.HotelApi
import com.example.hoteltest.domain.interfaces.api.ImageLoaderApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun provideLogInspector(): HttpLoggingInterceptor {
        val inspector = HttpLoggingInterceptor()
        inspector.level = HttpLoggingInterceptor.Level.BODY
        return inspector
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(inspector: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(inspector)
            .build()
        return okHttpClient
    }
    @Provides
    @Singleton
    fun provideHotelApi(okHttpClient: OkHttpClient):HotelApi{
        return Retrofit.Builder().baseUrl("https://run.mocky.io/v3/").addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build().create(HotelApi::class.java)
    }
    @Provides
    @Singleton
    fun provideImageLoaderApi(okHttpClient: OkHttpClient):ImageLoaderApi{
        return Retrofit.Builder().baseUrl("https://run.mocky.io/v3/").addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build().create(ImageLoaderApi::class.java)
    }

}