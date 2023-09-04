package com.example.hoteltest.presentation.rooms

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName

data class RoomsRecyclerItemData(
    val id:Long?,
    val name:String?,
    val price:String?,
    val pricePer:String?,
    val peculiarities:List<String>?,
    val images:List<Bitmap>?,
)
