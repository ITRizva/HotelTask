package com.example.hoteltest.domain.Models

import com.google.gson.annotations.SerializedName

data class RoomsInformationEntity(
    @SerializedName("rooms")
    val rooms:List<SingleRoomInformation>

)
data class SingleRoomInformation(
    @SerializedName("id")
    val id:Long,
    @SerializedName("name")
    val name:String,
    @SerializedName("price")
    val price:Int,
    @SerializedName("price_pre")
    val pricePre:String,
    @SerializedName("peculiarities")
    val peculiarities:List<String>,
    @SerializedName("image_urls")
    val imageUrls:List<String>,
)
