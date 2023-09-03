package com.example.hoteltest.presentation.rooms

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hoteltest.domain.usecases.GetHotelInformationUseCase
import com.example.hoteltest.domain.usecases.GetImageUseCase
import com.example.hoteltest.domain.usecases.GetRoomsInformationUseCase
import com.example.hoteltest.presentation.hotel.HotelViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    private val getRoomsInformation: GetRoomsInformationUseCase,
    private val getImage: GetImageUseCase
) : ViewModel() {

    private val _contentState = MutableLiveData<RoomsViewModelState>(RoomsViewModelState.Initial)
    val contentState: LiveData<RoomsViewModelState> = _contentState

    private val connectionScope = CoroutineScope(Dispatchers.IO)

    fun loadContent() {
        val state = _contentState.value
        when {
            state is RoomsViewModelState.Initial || state is RoomsViewModelState.Error -> updateData()
            else -> return
        }
    }

    private fun updateData() {
        _contentState.value = RoomsViewModelState.Loading
        connectionScope.launch {
            try {
                val roomsInformation = getRoomsInformation.execute()
                val roomList: ArrayList<RoomsRecyclerItemData> = arrayListOf()
                if (roomsInformation != null) {
                    roomsInformation.rooms.map {
                        roomList.add(
                            RoomsRecyclerItemData(
                                id = it.id,
                                name = it.name,
                                price = it.price,
                                pricePre = it.pricePre,
                                peculiarities = it.peculiarities,
                                images = it.imageUrls.map { url ->
                                    val responseImage = getImage.execute(url)?: null
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