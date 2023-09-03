package com.example.hoteltest.presentation.rooms

import android.graphics.Bitmap
import com.example.hoteltest.domain.Models.RoomsInformationEntity
import com.example.hoteltest.presentation.hotel.HotelViewModelState

sealed class RoomsViewModelState {
    object Initial: RoomsViewModelState()
    object Loading: RoomsViewModelState()
    data class RoomRecyclerContent(
        val rooms:List<RoomsRecyclerItemData>
    ): RoomsViewModelState()
    data class Error(val massage:String): RoomsViewModelState()
}