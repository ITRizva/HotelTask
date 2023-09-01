package com.example.hoteltest.presentation.hotel

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hoteltest.R
import com.example.hoteltest.databinding.FragmentHotelScreenBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotelScreen : Fragment() {
    private var binding: FragmentHotelScreenBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHotelScreenBinding.inflate(inflater,container,false)
        val list:List<Int>  = listOf(R.mipmap.first,R.mipmap.second,R.mipmap.third)
        val adapter = HotelViewPagerAdapter(list)
        binding?.viewPager2?.adapter = adapter
        binding?.chipGroup.add
        return binding?.root
    }
}