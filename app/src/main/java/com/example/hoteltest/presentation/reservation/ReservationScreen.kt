package com.example.hoteltest.presentation.reservation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hoteltest.R
import com.example.hoteltest.databinding.FragmentHotelScreenBinding
import com.example.hoteltest.databinding.FragmentReservationScreenBinding
import com.example.hoteltest.presentation.rooms.RoomsInformationAdapter
import com.example.hoteltest.presentation.rooms.RoomsScreen
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

@AndroidEntryPoint
class ReservationScreen : Fragment() {

    private var binding:FragmentReservationScreenBinding? = null
    private val viewModel by viewModels<ReservationViewModel>()

    private val recycler: ReservationRecyclerAdapter by lazy{ ReservationRecyclerAdapter { position:Int,personData:PersonRecyclerItem -> viewModel.writePersonData(position,personData)}}

    companion object{
        const val RESERVATION_SCREEN_VALUE = "RESERVATION_VALUE"
        @JvmStatic
        fun newInstance(value: Serializable): ReservationScreen {
            val transitValue: Bundle = Bundle().apply { putSerializable(RESERVATION_SCREEN_VALUE,value) }
            val fragment = ReservationScreen()
            fragment.arguments = transitValue
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentReservationScreenBinding.inflate(inflater, container, false)
        binding?.recyclerPersons?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding?.recyclerPersons?.adapter = recycler
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.personInformationItems.observe(viewLifecycleOwner){
            recycler.submitList(it)
        }

        viewModel.hotelRoomData.observe(viewLifecycleOwner){
            binding?.hotelRating?.text = it.hotel.ratingNumName
            binding?.hotelName?.text = it.hotel.name
            binding?.hotel?.text = it.hotel.name
            binding?.hotelAdress?.text = it.hotel.adress
            binding?.pricePer?.text = it.room.pricePer
            binding?.roomName?.text = it.room.name
            binding?.nutrition?.text = it.room.peculiarities?.get(0)
        }

        binding?.addPerson?.setOnClickListener {
            viewModel.addPerson()
        }
    }

    override fun onDestroyView() {
        viewModel.saveWrittenField()
        binding = null
        super.onDestroyView()
    }



}