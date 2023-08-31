package com.example.hoteltest.presentation.hotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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
        return binding?.root
    }
}