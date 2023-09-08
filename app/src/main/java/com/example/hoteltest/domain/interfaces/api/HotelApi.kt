package com.example.hoteltest.domain.interfaces.api

import com.example.hoteltest.domain.Models.HotelInformationEntity
import com.example.hoteltest.domain.Models.RoomsInformationEntity
import retrofit2.Response
import retrofit2.http.GET

//https://run.mocky.io/v3/
interface HotelApi {

        @GET("35e0d18e-2521-4f1b-a575-f0fe366f66e3")
        suspend fun getHotelInformation():Response<HotelInformationEntity>

        @GET("f9a38183-6f95-43aa-853a-9c83cbb05ecd")
        suspend fun getRoomInformation():Response<RoomsInformationEntity>
}