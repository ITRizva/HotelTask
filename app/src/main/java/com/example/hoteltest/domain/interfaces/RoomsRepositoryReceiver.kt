package com.example.hoteltest.domain.interfaces

import com.example.hoteltest.domain.Models.RoomsInformationEntity

interface RoomsRepositoryReceiver {
    suspend fun getRoomsInformation():RoomsInformationEntity?
}