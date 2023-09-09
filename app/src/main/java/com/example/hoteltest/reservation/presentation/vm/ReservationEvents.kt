package com.example.hoteltest.reservation.presentation.vm

import java.text.FieldPosition

sealed class ReservationEvents() {
    data class EmailPhoneError(val errorText:String):ReservationEvents()
    data class PersonInformationError(val errorText: String,val position: Int):ReservationEvents()
    object Success:ReservationEvents()
}