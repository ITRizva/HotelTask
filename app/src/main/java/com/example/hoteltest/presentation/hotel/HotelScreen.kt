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
import com.google.android.material.chip.Chip

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
        val chip = inflater.inflate(R.layout.chip_item,binding?.chipGroup,false) as Chip
        chip.text = "3-я линиия"
        binding?.chipGroup?.addView(chip)
        return binding?.root
    }
}