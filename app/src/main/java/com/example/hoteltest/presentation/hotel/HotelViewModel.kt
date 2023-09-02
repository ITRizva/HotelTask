package com.example.hoteltest.presentation.hotel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hoteltest.domain.usecases.GetHotelInformationUseCase
import com.example.hoteltest.domain.usecases.GetImageUseCase
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

    private fun updateData() {
        _contentState.value = HotelViewModelState.Loading
        connectionScope.launch {
            try {
                val hotelInformation = getHotelInformation.execute()
                if (hotelInformation != null) {
                    val chipList =
                    _contentState.postValue(
                        HotelViewModelState.HotelScreenContent(
                            id = hotelInformation.id,
                            name = hotelInformation.name,
                            adress = hotelInformation.adress,
                            minimalPrice = "от ${hotelInformation.minimalPrice}",
                            priceForIt = hotelInformation.priceForIt,
                            ratingNumName = "${hotelInformation.rating} ${hotelInformation.ratingName}",
                            description = hotelInformation.aboutTheHotel.description,
                            peculiarities = hotelInformation.aboutTheHotel.peculiarities
                        )
                    )
                } else {
                    _contentState.postValue(HotelViewModelState.Error("Данных нет "))
                }
            } catch (io:Exception){
                _contentState.postValue(HotelViewModelState.Error("404"))
            }
        }
    }

}