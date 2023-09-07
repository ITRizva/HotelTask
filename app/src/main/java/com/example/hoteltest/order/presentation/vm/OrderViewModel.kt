package com.example.hoteltest.order.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.hoteltest.order.presentation.ui.OrderFragment
import com.example.hoteltest.reservation.presentation.vm.OrderSerializeData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle): ViewModel()  {

    private val _orderDataState:MutableLiveData<OrderSerializeData> = MutableLiveData()
    val orderData: LiveData<OrderSerializeData>  = _orderDataState

    init{
        _orderDataState.value = savedStateHandle.get<OrderSerializeData>(OrderFragment.ORDER_FRAGMENT_VALUE)
    }
}