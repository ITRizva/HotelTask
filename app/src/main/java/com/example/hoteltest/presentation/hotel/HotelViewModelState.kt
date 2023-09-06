package com.example.hoteltest.presentation.hotel

import android.graphics.Bitmap
import java.io.Serializable

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
        val imageList:ArrayList<Bitmap>,
        val description:String,
        val peculiarities:List<String>
    ):HotelViewModelState()
    data class Error(val massage:String):HotelViewModelState()
}
