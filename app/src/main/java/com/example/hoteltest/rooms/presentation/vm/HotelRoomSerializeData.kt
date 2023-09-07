package com.example.hoteltest.rooms.presentation.vm

import com.example.hoteltest.hotel.presentation.vm.HotelSerializeData
import java.io.Serializable

data class HotelRoomSerializeData(
    val room: RoomSerializeData,
    val hotel: HotelSerializeData
):Serializable