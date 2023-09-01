package com.example.hoteltest.data

import com.example.hoteltest.domain.Models.HotelInformationEntity
import com.example.hoteltest.domain.Models.RoomsInformationEntity
import com.example.hoteltest.domain.interfaces.HotelRepositoryReceiver
import com.example.hoteltest.domain.interfaces.ImageRepositoryReceiver
import com.example.hoteltest.domain.interfaces.RoomsRepositoryReceiver
import com.example.hoteltest.domain.interfaces.api.HotelApi
import com.example.hoteltest.domain.interfaces.api.ImageLoaderApi
import okhttp3.ResponseBody
import javax.inject.Inject

class HotelInformationRepositoryImp @Inject constructor(
    private val hotelApi: HotelApi,
    private val imageloaderApi: ImageLoaderApi
) : HotelRepositoryReceiver,
    RoomsRepositoryReceiver,
    ImageRepositoryReceiver {
    override suspend fun getHotelInfo(): HotelInformationEntity? {
        return hotelApi.getHotelInformation().body()
    }

    override suspend fun getImage(url: String): ResponseBody? {
        return imageloaderApi.getImage(url).body()
    }

    override suspend fun getRoomsInformation(): RoomsInformationEntity? {
        return hotelApi.getRoomInformation().body()
    }

}