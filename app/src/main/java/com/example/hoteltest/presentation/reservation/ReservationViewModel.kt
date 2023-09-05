package com.example.hoteltest.presentation.reservation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle): ViewModel() {

    //private val _travelInformation:MutableLiveData<>

    private val _personInformationItems:MutableLiveData<List<PersonRecyclerItem>> = MutableLiveData(listOf())
    val personInformationItems:LiveData<List<PersonRecyclerItem>> = _personInformationItems

    fun addPerson() {
        _personInformationItems.value?.size?.let {
            if(it < 5){
                _personInformationItems.value = _personInformationItems.value?.plus(PersonRecyclerItem(itemLabel = "${_personInformationItems.value?.size?.toOrdinalNumeral()} турист"))
            }
        }
    }

    fun writePersonData(position:Int,personRecyclerItem: PersonRecyclerItem){
        val newList = _personInformationItems.value?.toMutableList()
        newList?.set(position, personRecyclerItem)
        newList?.let{_personInformationItems.value = it}
    }

    private fun Int.toOrdinalNumeral():String{
        return when(this){
            0->"Первый"
            1->"Второй"
            2->"Третий"
            3->"Четвертый"
            4->"Пятый"
            else -> {""}
        }
    }
}