package com.example.hoteltest.rooms.presentation.ui

import android.graphics.Bitmap
import java.io.Serializable

data class RoomsRecyclerItemData(
    val id:Long?,
    val name:String?,
    val price:String?,
    val pricePer:String?,
    val peculiarities:List<String>?,
    val images:List<Bitmap>?,
):Serializable