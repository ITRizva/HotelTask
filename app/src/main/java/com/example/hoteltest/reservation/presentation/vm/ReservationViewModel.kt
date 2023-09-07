package com.example.hoteltest.reservation.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.hoteltest.NavigatorInterface
import com.example.hoteltest.order.presentation.ui.OrderFragment
import com.example.hoteltest.reservation.presentation.ui.PersonRegistrationItem
import com.example.hoteltest.reservation.presentation.ui.ReservationFragment
import com.example.hoteltest.rooms.presentation.vm.HotelRoomSerializeData
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.Serializable
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle) :
    ViewModel() {



    private val _personsList: MutableLiveData<MutableList<PersonRegistrationItem>> = MutableLiveData(mutableListOf(PersonRegistrationItem("Первый турист")))

    private val _reservationData:MutableLiveData<ReservationDataState> = MutableLiveData()
    val reservationData:LiveData<ReservationDataState> = _reservationData

    private val _personInformationItems: MutableLiveData<List<PersonRegistrationItem>> = MutableLiveData(_personsList.value)
    val personInformationItems: LiveData<List<PersonRegistrationItem>> = _personInformationItems

    init {
        val receivedData = savedStateHandle.get<Serializable>(ReservationFragment.RESERVATION_SCREEN_VALUE) as HotelRoomSerializeData
        val fuelPrice = 9300//эти данные должны быть
        val servicePrice = 2136//эти данные должны быть где-то получены
        val tour = (receivedData.room.price?.toInt()?.minus((fuelPrice+servicePrice)))
        _reservationData.value = ReservationDataState(
            hotelname = receivedData.hotel.name,
            price = receivedData.room.price,
            pricePer = receivedData.room.pricePer,
            peculiarities = receivedData.room.peculiarities,
            roomname = receivedData.room.name,
            adress = receivedData.hotel.adress,
            priceForIt = receivedData.hotel.priceForIt,
            ratingNumName = receivedData.hotel.ratingNumName,
            description = receivedData.hotel.description,
            fuel = fuelPrice,
            service = servicePrice,
            tour = tour,
            fullPrice = receivedData.room.price,
            phoneNumber = null,
            email = null
            )
    }

    fun addPerson() {
        _personInformationItems.value?.size?.let {
            if (it < 5) {
                _personsList.value = _personsList.value?.plus(PersonRegistrationItem(itemLabel = "${_personInformationItems.value?.size?.toOrdinalNumeral()} турист")) ?.toMutableList()
                _personInformationItems.value = _personsList.value
            }
        }
        val fullPrice = (_personsList.value?.size?.let { _reservationData.value?.price?.toInt()?.times(it) })
        _reservationData.value = _reservationData.value?.copy(fullPrice = fullPrice.toString() )
    }

    fun setEmail(email:String){
        _reservationData.value = _reservationData.value?.copy(email = email)
    }

    fun setPhone(phoneNumber:String){
        _reservationData.value = _reservationData.value?.copy(phoneNumber = phoneNumber )
    }

    fun writePersonData(position: Int, PersonRegistrationItem: PersonRegistrationItem) {
        val newList = _personsList.value
        newList?.let { it[position] = PersonRegistrationItem }
        newList?.let { _personsList.value = it }
    }

    fun openOrderFragment(navigator:NavigatorInterface){
        val orderData = OrderSerializeData(_reservationData.value!!, _personsList.value!!)
        navigator.replaceScreen(OrderFragment.newInstance(orderData))
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


}