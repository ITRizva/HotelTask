package com.example.hoteltest.presentation.hotel

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.hoteltest.databinding.FragmentHotelScreenBinding

class HotelViewPagerAdapter(private val images:List<Int>):RecyclerView.Adapter<HotelViewPagerAdapter.ViewPagerViewHolder> (){
    inner class ViewPagerViewHolder(private val binding:FragmentHotelScreenBinding):RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HotelViewPagerAdapter.ViewPagerViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: HotelViewPagerAdapter.ViewPagerViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}