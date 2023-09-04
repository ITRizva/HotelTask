package com.example.hoteltest.presentation.reservation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hoteltest.R
import com.example.hoteltest.databinding.FragmentHotelScreenBinding
import com.example.hoteltest.databinding.FragmentReservationScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationScreen : Fragment() {

    private var binding:FragmentReservationScreenBinding? = null
    private val viewModel by viewModels<ReservationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReservationScreenBinding.inflate(inflater, container, false)
        return binding?.root
    }
}