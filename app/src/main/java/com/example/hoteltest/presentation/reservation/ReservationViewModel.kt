package com.example.hoteltest.presentation.reservation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle): ViewModel() {

}