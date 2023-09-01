package com.example.hoteltest.domain.interfaces

import com.example.hoteltest.domain.Models.HotelInformationEntity

interface HotelRepositoryReceiver {
    suspend fun getHotelInfo():HotelInformationEntity?
}