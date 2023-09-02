package com.example.hoteltest.domain.Models

import com.google.gson.annotations.SerializedName

data class AboutHotel(
    @SerializedName("description")
    val description:String,
    @SerializedName("peculiarities")
    val peculiarities:List<String>
)