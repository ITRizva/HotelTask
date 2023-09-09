package com.example.hoteltest.reservation.presentation.ui

data class PersonRegistrationItem(
    val itemLabel:String,
    val isCorrect:Boolean = true,
    val name:String? = null,
    val surName:String? = null,
    val bornDate:String? = null,
    val citizenShip:String? = null,
    val numIntPassport:String? = null,
    val durationIntPassport:String? = null
)