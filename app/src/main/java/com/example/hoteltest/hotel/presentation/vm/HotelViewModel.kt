package com.example.hoteltest.hotel.presentation.vm

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hoteltest.NavigatorInterface
import com.example.hoteltest.domain.usecases.GetHotelInformationUseCase
import com.example.hoteltest.domain.usecases.GetImageUseCase
import com.example.hoteltest.rooms.presentation.ui.RoomsFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(
    private val getHotelInformation: GetHotelInformationUseCase,
    private val getImage: GetImageUseCase
) : ViewModel() {

    private val _contentState = MutableLiveData<HotelViewModelState>(HotelViewModelState.Initial)
    val contentState: LiveData<HotelViewModelState> = _contentState

    private val connectionScope = CoroutineScope(Dispatchers.IO)


    fun loadContent() {
        val state = _contentState.value
        when {
            state is HotelViewModelState.Initial || state is HotelViewModelState.Error -> updateData()
            else -> return
        }
    }

    fun openRoomScreen(navigator: NavigatorInterface) {
        (_contentState.value as? HotelViewModelState.HotelFragmentContent)?.let {
            val hotelInformation = HotelSerializeData(
                name = it.name,
                adress = it.adress,
                priceForIt = it.priceForIt,
                ratingNumName = it.ratingNumName,
                description = it.description,
            )
            val roomFragment = RoomsFragment.newInstance(hotelInformation)
            navigator.replaceScreen(roomFragment)
        }
    }

    private fun updateData() {
        _contentState.value = HotelViewModelState.Loading
        connectionScope.launch {
            try {
                val hotelInformation = getHotelInformation.execute()
                if (hotelInformation != null) {
                    val imageList: ArrayList<Bitmap> = arrayListOf()
                    hotelInformation.imageUrls.forEach { url ->
                        val responseImage = getImage.execute(url)
                        val bitmapImage = BitmapFactory.decodeStream(responseImage?.byteStream())
                        imageList.add(bitmapImage)
                    }
                    _contentState.postValue(
                        HotelViewModelState.HotelFragmentContent(
                            id = hotelInformation.id,
                            name = hotelInformation.name,
                            adress = hotelInformation.adress,
                            minimalPrice = "от ${hotelInformation.minimalPrice} ₽",
                            priceForIt = hotelInformation.priceForIt,
                            ratingNumName = "${hotelInformation.rating} ${hotelInformation.ratingName}",
                            description = hotelInformation.aboutTheHotel.description,
                            peculiarities = hotelInformation.aboutTheHotel.peculiarities,
                            imageList = imageList
                        )
                    )
                } else {
                    _contentState.postValue(HotelViewModelState.Error("Данных нет "))
                }
            } catch (io: Exception) {
                _contentState.postValue(HotelViewModelState.Error("404"))
            }
        }
    }

}