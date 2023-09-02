package com.example.hoteltest.presentation.hotel

sealed class HotelViewModelState{
    object Initial:HotelViewModelState()
    object Loading:HotelViewModelState()
    data class HotelScreenContent(
        val id:Long,
        val name:String,
        val adress:String,
        val minimalPrice: String,
        val priceForIt:String,
        val ratingNumName:String,

        val description:String,
        val peculiarities:List<String>
    ):HotelViewModelState()
    data class Error(val massage:String):HotelViewModelState()
}
