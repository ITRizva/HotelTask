package com.example.hoteltest.hotel.presentation.ui

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hoteltest.databinding.HotelViewpagerItemBinding

class ViewPagerAdapter(private val images: List<Bitmap>?) :
    RecyclerView.Adapter<ViewPagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder = ViewPagerViewHolder.createList(parent)

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currentImage = images?.get(position)
        currentImage?.let { holder.drawItem(it) }
    }

    override fun getItemCount(): Int = images?.size ?: 0
}

class ViewPagerViewHolder(private val binding: HotelViewpagerItemBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun createList(list: ViewGroup): ViewPagerViewHolder = ViewPagerViewHolder(
            HotelViewpagerItemBinding.inflate(
                (LayoutInflater.from(list.context)),
                list,
                false
            )
        )
    }

    fun drawItem(image:Bitmap){
        binding.hotelImage.setImageBitmap(image)
    }
}