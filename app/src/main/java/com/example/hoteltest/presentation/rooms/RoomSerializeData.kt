package com.example.hoteltest.presentation.rooms

import java.io.Serializable

data class RoomSerializeData(
    val name:String?,
    val price:String?,
    val pricePer:String?,
    val peculiarities:List<String>?,
):Serializable
