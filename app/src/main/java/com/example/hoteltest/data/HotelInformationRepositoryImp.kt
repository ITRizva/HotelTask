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
        return if(hotelApi.getHotelInformation().isSuccessful) hotelApi.getHotelInformation().body() else null
    }

    override suspend fun getImage(url: String): ResponseBody? {
        return if(imageloaderApi.getImage(url).isSuccessful) imageloaderApi.getImage(url).body() else null
    }

    override suspend fun getRoomsInformation(): RoomsInformationEntity? {
        return if(hotelApi.getRoomInformation().isSuccessful) hotelApi.getRoomInformation().body() else null
    }

}