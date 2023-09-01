package com.example.hoteltest.presentation.hotel

import androidx.lifecycle.ViewModel
import com.example.hoteltest.domain.usecases.GetHotelInformationUseCase
import com.example.hoteltest.domain.usecases.GetImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(
    private val getHotelInformation: GetHotelInformationUseCase,
    private val getImage: GetImageUseCase
) : ViewModel() {

}