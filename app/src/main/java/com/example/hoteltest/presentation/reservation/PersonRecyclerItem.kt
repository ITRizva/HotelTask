package com.example.hoteltest.presentation.reservation

data class PersonRecyclerItem(
    val itemLabel:String,
    val name:String = "",
    val surName:String = "",
    val bornDate:String = "",
    val citizenShip:String = "",
    val numIntPassport:String = "",
    val durationIntPassport:String = ""
)