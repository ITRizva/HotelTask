package com.example.hoteltest.presentation.rooms

import com.example.hoteltest.presentation.hotel.HotelSerializeData
import com.example.hoteltest.presentation.rooms.RoomSerializeData
import com.example.hoteltest.presentation.rooms.RoomsRecyclerItemData
import java.io.Serializable

data class ReservationSerializeData(
    val room: RoomSerializeData,
    val hotel: HotelSerializeData
):Serializable
