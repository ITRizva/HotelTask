package com.example.hoteltest.reservation.presentation.vm

data class ReservationDataState(
    val hotelname:String?,
    val price:String?,
    val pricePer:String?,
    val peculiarities:List<String>?,
    val roomname:String?,
    val adress:String?,
    val priceForIt:String?,
    val ratingNumName:String?,
    val description:String?,
    val tour:Int?,
    val fuel:Int?,
    val service:Int? ,
    val fullPrice:String?,
    val phoneNumber:String?,
    val email:String?
)
