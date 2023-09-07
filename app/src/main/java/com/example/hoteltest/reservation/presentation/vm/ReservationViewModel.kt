package com.example.hoteltest.reservation.presentation.vm

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.hoteltest.rooms.presentation.vm.ReservationSerializeData
import com.example.hoteltest.reservation.presentation.ui.PersonRecyclerItem
import com.example.hoteltest.reservation.presentation.ui.ReservationFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle) :
    ViewModel() {

    private val personsList: MutableLiveData<MutableList<PersonRecyclerItem>> = MutableLiveData(mutableListOf(PersonRecyclerItem("Первый турист")))

    private val _hotelRoomData = savedStateHandle.getLiveData<ReservationSerializeData>(
        ReservationFragment.RESERVATION_SCREEN_VALUE
    )
    val hotelRoomData:LiveData<ReservationSerializeData> = _hotelRoomData

    private val _personInformationItems: MutableLiveData<List<PersonRecyclerItem>> = MutableLiveData(personsList.value)
    val personInformationItems: LiveData<List<PersonRecyclerItem>> = _personInformationItems

    private val _travelPrice:MutableLiveData<TravelPrice> = MutableLiveData()
    val travelPrice:LiveData<TravelPrice> = _travelPrice

    fun addPerson() {
        _personInformationItems.value?.size?.let {
            if (it < 5) {
                personsList.value = personsList.value?.plus(PersonRecyclerItem(itemLabel = "${_personInformationItems.value?.size?.toOrdinalNumeral()} турист")) ?.toMutableList()
                _personInformationItems.value = personsList.value
            }
        }
//        val /// устал нужно написать логику для подсчета соимотси перелета с учетом топливных вычетов они костанта как я понял умножать цену на размер массива персон
//        val travelPrice = travelPrice.value?.copy(fullPrice = )
    }

    fun writePersonData(position: Int, personRecyclerItem: PersonRecyclerItem) {
        val newList = personsList.value
        Log.d("Cleared VM","vm is written")
        newList?.let { it[position] = personRecyclerItem }
        newList?.let { personsList.value = it }
    }

    private fun Int.toOrdinalNumeral(): String {
        return when (this) {
            0 -> "Первый"
            1 -> "Второй"
            2 -> "Третий"
            3 -> "Четвертый"
            4 -> "Пятый"
            else -> {
                ""
            }
        }
    }

    private fun String.isEmailValid(): Boolean = !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun String.isPhoneValid():Boolean = Regex(""".\d..\d{3}..\d{3}-\d{2}-\d{2}""").matches(this)
}