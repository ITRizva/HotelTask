package com.example.hoteltest.presentation.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hoteltest.databinding.FragmentHotelScreenBinding
import com.example.hoteltest.databinding.FragmentRoomsScreenBinding
import com.example.hoteltest.databinding.RoomsRecyclerItemBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notify

@AndroidEntryPoint
class RoomsScreen : Fragment() {

    private var binding:FragmentRoomsScreenBinding? = null
    private val viewModel by viewModels<RoomsViewModel>()

    private val recycler:RoomsInformationAdapter by lazy{ RoomsInformationAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadContent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoomsScreenBinding.inflate(inflater, container, false)
        binding?.roomRecycler?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding?.roomRecycler?.adapter = recycler
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.contentState.observe(viewLifecycleOwner){
            when(it){
                is RoomsViewModelState.RoomRecyclerContent ->{

                    recycler.submitList(it.rooms)
                    recycler.notifyDataSetChanged()

                }

                else -> {}
            }

        }

    }


}