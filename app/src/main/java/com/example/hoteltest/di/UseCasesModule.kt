package com.example.hoteltest.di

import com.example.hoteltest.data.HotelInformationRepositoryImp
import com.example.hoteltest.domain.interfaces.HotelRepositoryReceiver
import com.example.hoteltest.domain.interfaces.ImageRepositoryReceiver
import com.example.hoteltest.domain.interfaces.RoomsRepositoryReceiver
import com.example.hoteltest.domain.interfaces.api.HotelApi
import com.example.hoteltest.domain.interfaces.api.ImageLoaderApi
import com.example.hoteltest.domain.usecases.GetHotelInformationUseCase
import com.example.hoteltest.domain.usecases.GetImageUseCase
import com.example.hoteltest.domain.usecases.GetRoomsInformationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    @Singleton
    fun provideHotelInformationRepositoryImp(hotelApi: HotelApi,imageLoaderApi: ImageLoaderApi): HotelInformationRepositoryImp {
        return HotelInformationRepositoryImp(hotelApi,imageLoaderApi)
    }
    @Provides
    @Singleton
    fun provideHotelRepositoryReceiver(repositoryImp:HotelInformationRepositoryImp):HotelRepositoryReceiver{
        return repositoryImp
    }
    @Provides
    @Singleton
    fun provideRoomsRepositoryReceiver(repositoryImp:HotelInformationRepositoryImp):RoomsRepositoryReceiver{
        return repositoryImp
    }
    @Provides
    @Singleton
    fun provideImageRepositoryReceiver(repositoryImp:HotelInformationRepositoryImp):ImageRepositoryReceiver{
        return repositoryImp
    }

    @Provides
    @Singleton
    fun provideGetHotelInformationUseCase(repository:HotelRepositoryReceiver):GetHotelInformationUseCase{
        return GetHotelInformationUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideGetRoomsInformationUseCase(repository:RoomsRepositoryReceiver):GetRoomsInformationUseCase{
        return GetRoomsInformationUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideGetImageUseCase(repository:ImageRepositoryReceiver):GetImageUseCase{
        return GetImageUseCase(repository)
    }
}