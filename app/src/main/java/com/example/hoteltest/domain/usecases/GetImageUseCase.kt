package com.example.hoteltest.domain.usecases

import com.example.hoteltest.domain.interfaces.ImageRepositoryReceiver
import okhttp3.Response
import okhttp3.ResponseBody
import javax.inject.Inject

class GetImageUseCase @Inject constructor(private val repository:ImageRepositoryReceiver) {
    suspend fun execute(url:String):ResponseBody? = repository.getImage(url)
}