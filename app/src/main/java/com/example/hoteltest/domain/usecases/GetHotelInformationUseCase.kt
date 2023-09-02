package com.example.hoteltest.domain.usecases

import com.example.hoteltest.domain.Models.HotelInformationEntity
import com.example.hoteltest.domain.interfaces.HotelRepositoryReceiver
import javax.inject.Inject

class GetHotelInformationUseCase @Inject constructor(private val repository:HotelRepositoryReceiver) {
    suspend fun execute():HotelInformationEntity?{

        return repository.getHotelInfo()
    }
}