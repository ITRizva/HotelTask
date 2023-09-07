package com.example.hoteltest.hotel.presentation.vm

import java.io.Serializable

data class HotelSerializeData(
    val name:String,
    val adress:String,
    val priceForIt:String,
    val ratingNumName:String,
    val description:String,
):Serializable