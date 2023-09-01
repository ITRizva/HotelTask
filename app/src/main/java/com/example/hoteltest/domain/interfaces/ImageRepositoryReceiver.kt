package com.example.hoteltest.domain.interfaces

import okhttp3.ResponseBody

interface ImageRepositoryReceiver {
    suspend fun getImage(url:String):ResponseBody?
}