package com.example.hoteltest.rooms.presentation.vm

import java.io.Serializable

data class RoomSerializeData(
    val name:String?,
    val price:String?,
    val pricePer:String?,
    val peculiarities:List<String>?,
):Serializable