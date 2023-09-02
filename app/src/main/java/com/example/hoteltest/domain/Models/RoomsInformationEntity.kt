package com.example.hoteltest.domain.Models

import com.google.gson.annotations.SerializedName

data class RoomsInformationEntity(
    @SerializedName("rooms")
    val rooms:List<SingleRoomInformation>

)

