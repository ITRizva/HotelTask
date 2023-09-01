package com.example.hoteltest.domain.usecases

import com.example.hoteltest.domain.Models.RoomsInformationEntity
import com.example.hoteltest.domain.interfaces.RoomsRepositoryReceiver
import javax.inject.Inject

class GetRoomsInformationUseCase @Inject constructor(private val repository:RoomsRepositoryReceiver) {
    suspend fun execute():RoomsInformationEntity?{
        return repository.getRoomsInformation()
    }
}