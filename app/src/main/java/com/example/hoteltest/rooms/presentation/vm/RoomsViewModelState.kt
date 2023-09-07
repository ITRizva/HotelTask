package com.example.hoteltest.rooms.presentation.vm

import com.example.hoteltest.rooms.presentation.ui.RoomsRecyclerItemData

sealed class RoomsViewModelState {
    object Initial: RoomsViewModelState()
    object Loading: RoomsViewModelState()
    data class RoomRecyclerContent(
        val rooms:List<RoomsRecyclerItemData>
    ): RoomsViewModelState()
    data class Error(val massage:String): RoomsViewModelState()
}