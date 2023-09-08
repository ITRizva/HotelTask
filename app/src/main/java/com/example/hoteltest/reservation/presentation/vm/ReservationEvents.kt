package com.example.hoteltest.reservation.presentation.vm

sealed class ReservationEvents() {
    data class EmailPhoneError(val errorText:String):ReservationEvents()
    data class PersonInformationError(val errorText: String):ReservationEvents()
    object Success:ReservationEvents()
}