package com.example.hoteltest.domain.Models

import com.google.gson.annotations.SerializedName

data class HotelInformationEntity(
    @SerializedName("id")
    val id:Long,
    @SerializedName("name")
    val name:String,
    @SerializedName("adress")
    val adress:String,
    @SerializedName("minimal_price")
    val minimalPrice:Long,
    @SerializedName("price_for_it")
    val priceForIt:String,
    @SerializedName("rating")
    val rating:Int,
    @SerializedName("rating_name")
    val ratingName:String,
    @SerializedName("image_urls")
    val imageUrls:List<String>,
    @SerializedName("about_the_hotel")
    val aboutTheHotel:AboutHotel
)

