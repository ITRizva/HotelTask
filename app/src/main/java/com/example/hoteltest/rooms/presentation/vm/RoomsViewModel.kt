package com.example.hoteltest.rooms.presentation.vm

import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hoteltest.navigation.NavigatorInterface
import com.example.hoteltest.domain.usecases.GetImageUseCase
import com.example.hoteltest.domain.usecases.GetRoomsInformationUseCase
import com.example.hoteltest.hotel.presentation.vm.HotelSerializeData
import com.example.hoteltest.reservation.presentation.ui.ReservationFragment
import com.example.hoteltest.rooms.presentation.ui.RoomsFragment
import com.example.hoteltest.rooms.presentation.ui.RoomsRecyclerItemData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.Serializable
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    private val getRoomsInformation: GetRoomsInformationUseCase,
    private val getImage: GetImageUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val navigator:NavigatorInterface
) : ViewModel() {

    private val _contentState = MutableLiveData<RoomsViewModelState>(RoomsViewModelState.Initial)
    val contentState: LiveData<RoomsViewModelState> = _contentState

    private val _hotelName:MutableLiveData<String> = MutableLiveData()
    val hotelName:LiveData<String> = _hotelName

    private val argumentsHotel = savedStateHandle.get<Serializable>(RoomsFragment.ROOM_SCREEN_VALUE) as HotelSerializeData

    fun loadContent() {
        _hotelName.value = argumentsHotel.name
        val state = _contentState.value
        when {
            state is RoomsViewModelState.Initial || state is RoomsViewModelState.Error -> updateData()
            else -> return
        }
    }

    fun openReservationFragment(position:Int){
        (_contentState.value as? RoomsViewModelState.RoomRecyclerContent)?.let {
            val room = it.rooms[position]
            val argumentsRoom = RoomSerializeData(
                name = room.name,
                price = room.price,
                pricePer = room.pricePer,
                peculiarities = room.peculiarities)
            val roomData = HotelRoomSerializeData(argumentsRoom,argumentsHotel)
            navigator.replaceScreen(roomData,ReservationFragment.RESERVATION_SCREEN_VALUE)
        }
    }

    fun stepBack(){
        navigator.stepBack()
    }

    private fun updateData() {
        _contentState.value = RoomsViewModelState.Loading
        viewModelScope.launch {
            try {
                val roomsInformation = getRoomsInformation.execute()
                val roomList: ArrayList<RoomsRecyclerItemData> = arrayListOf()
                if (roomsInformation != null) {
                    roomsInformation.rooms.map {
                        roomList.add(
                            RoomsRecyclerItemData(
                                id = it.id,
                                name = it.name,
                                price = it.price.toString(),
                                pricePer = it.pricePer,
                                peculiarities = it.peculiarities,
                                images = it.imageUrls.map { url ->
                                    val responseImage = getImage.execute(url)
                                    BitmapFactory.decodeStream(responseImage?.byteStream())
                                }
                            )
                        )
                    }
                    _contentState.postValue(RoomsViewModelState.RoomRecyclerContent(rooms = roomList))
                }
            } catch (io: Exception) {
                _contentState.postValue(RoomsViewModelState.Error("404"))
            }
        }
    }
}