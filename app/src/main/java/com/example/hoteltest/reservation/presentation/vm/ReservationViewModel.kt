package com.example.hoteltest.reservation.presentation.vm

import android.content.Context
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.hoteltest.R
import com.example.hoteltest.navigation.NavigatorInterface
import com.example.hoteltest.order.presentation.ui.OrderFragment
import com.example.hoteltest.reservation.presentation.ui.PersonRegistrationItem
import com.example.hoteltest.reservation.presentation.ui.ReservationFragment
import com.example.hoteltest.rooms.presentation.vm.HotelRoomSerializeData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.Serializable
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val navigator: NavigatorInterface,
    @ApplicationContext private val context: Context
) :
    ViewModel() {

    private val _personsList: MutableLiveData<MutableList<PersonRegistrationItem>> = MutableLiveData(mutableListOf(PersonRegistrationItem("Первый турист")))

    private val _reservationData: MutableLiveData<ReservationDataState> = MutableLiveData()
    val reservationData: LiveData<ReservationDataState> = _reservationData

    private val _event:MutableLiveData<ReservationEvents> = MutableLiveData()
    val event:LiveData<ReservationEvents> = _event

    init {
        val receivedData =
            savedStateHandle.get<Serializable>(ReservationFragment.RESERVATION_SCREEN_VALUE) as HotelRoomSerializeData
        val fuelPrice = 9300//эти данные должны быть укащаны ранее по кейсу
        val servicePrice = 2136//эти данные должны быть укащаны ранее по кейсу
        val tour = (receivedData.room.price?.toInt()?.minus((fuelPrice + servicePrice)))
        _reservationData.value = _personsList.value?.let {
            ReservationDataState(
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
                email = null,
                personList = it
            )
        }
    }

    fun addPerson() {
        _reservationData.value?.let {
            if (it.personList.size < 5) {
                _personsList.value =
                    _personsList.value?.plus(PersonRegistrationItem(itemLabel = "${it.personList.size.toOrdinalNumeral()} турист"))
                        ?.toMutableList()
                _reservationData.value =
                    _personsList.value?.let { personList -> it.copy(personList = personList) }
            }
        }
        val fullPrice =
            (_personsList.value?.size?.let { _reservationData.value?.price?.toInt()?.times(it) })
        _reservationData.value = _reservationData.value?.copy(fullPrice = fullPrice.toString())
    }

    fun setEmail(email: String) {
        _reservationData.value = _reservationData.value?.copy(email = email)
    }

    fun setPhone(phoneNumber: String) {
        _reservationData.value = _reservationData.value?.copy(phoneNumber = phoneNumber)
    }

    fun writePersonData(position: Int, PersonRegistrationItem: PersonRegistrationItem) {
        val newList = _personsList.value
        newList?.let { it[position] = PersonRegistrationItem }
        newList?.let { _personsList.value = it }
    }

    fun openOrderFragment() {
        _reservationData.value = _personsList.value?.let { _reservationData.value?.copy(personList = it) }
        _event.value = checkReservationData()
        if(_event.value is ReservationEvents.Success){
            val orderData = _reservationData.value?.let { OrderSerializeData(it) }
            orderData?.let { navigator.replaceScreen(it, OrderFragment.ORDER_FRAGMENT_VALUE) }
        }
    }

    fun setErrorOnPerson(position:Int){
        val list = _reservationData.value?.personList
        val element = list?.get(position)?.copy(isCorrect = false)
        element?.let { list.set(position, it) }
        list?.let{_reservationData.value = _reservationData.value?.copy( personList = list)}
    }

    private fun checkReservationData():ReservationEvents{
        val phoneNum = _reservationData.value?.phoneNumber
        val email = _reservationData.value?.email
        if(phoneNum == null || email == null) return ReservationEvents.EmailPhoneError(context.resources.getString(R.string.num_email_error))
        if(!phoneNum.isPhoneValid() || !email.isEmailValid()) return ReservationEvents.EmailPhoneError(context.resources.getString(R.string.num_email_error))
        val positionError = checkPersonList()
        if(positionError != -1) return ReservationEvents.PersonInformationError(context.resources.getString(R.string.person_error),positionError)
        return ReservationEvents.Success
    }

    private fun checkPersonList():Int{
        _reservationData.value?.personList?.forEachIndexed { index, personRegistrationItem ->
            if(!personRegistrationItem.fieldIsNotNull()) return index
        }
        return -1
    }

    private fun PersonRegistrationItem.fieldIsNotNull():Boolean{
        if(name.isNullOrEmpty()) return false
        if(surName.isNullOrEmpty()) return false
        if(bornDate.isNullOrEmpty()) return false
        if(citizenShip.isNullOrEmpty()) return false
        if(numIntPassport.isNullOrEmpty()) return false
        if(durationIntPassport.isNullOrEmpty()) return false
        return true
    }

    private fun String.isEmailValid(): Boolean = !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun String.isPhoneValid(): Boolean = Regex(""".\d..\d{3}..\d{3}-\d{2}-\d{2}""").matches(this)

    private fun Int.toOrdinalNumeral(): String {
        return when (this) {
            0 -> "Первый"
            1 -> "Второй"
            2 -> "Третий"
            3 -> "Четвертый"
            4 -> "Пятый"
            else -> {""}
        }
    }
}