package com.example.hoteltest.reservation.presentation.vm

import com.example.hoteltest.reservation.presentation.ui.PersonRegistrationItem
import java.io.Serializable

data class OrderSerializeData(
    val reservationData:ReservationDataState
):Serializable
