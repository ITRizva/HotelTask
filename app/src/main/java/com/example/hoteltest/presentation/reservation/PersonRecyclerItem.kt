package com.example.hoteltest.presentation.reservation

data class PersonRecyclerItem(
    val itemLabel:String,
    val name:String? = null,
    val surName:String? = null,
    val bornDate:String? = null,
    val citizenShip:String? = null,
    val numIntPassport:String? = null,
    val durationIntPassport:String? = null
)