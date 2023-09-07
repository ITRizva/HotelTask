package com.example.hoteltest.rooms.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hoteltest.binding.BaseFragment
import com.example.hoteltest.databinding.FragmentRoomsFragmentBinding
import com.example.hoteltest.navigator
import com.example.hoteltest.rooms.presentation.vm.RoomsViewModel
import com.example.hoteltest.rooms.presentation.vm.RoomsViewModelState
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

@AndroidEntryPoint
class RoomsFragment : BaseFragment<FragmentRoomsFragmentBinding>() {

    private val viewModel by viewModels<RoomsViewModel>()

    private val recycler: RoomsInformationAdapter by lazy{ RoomsInformationAdapter(context){ position-> viewModel.openReservationFragment(navigator(),position)} }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentRoomsFragmentBinding = FragmentRoomsFragmentBinding.inflate(inflater,container,false)

    companion object{
        const val ROOM_SCREEN_VALUE = "ROOM_SCREEN_VALUE"
        @JvmStatic
        fun newInstance(value: Serializable): RoomsFragment {
            val transitValue: Bundle = Bundle().apply { putSerializable(ROOM_SCREEN_VALUE,value) }
            val fragment = RoomsFragment()
            fragment.arguments = transitValue
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.roomRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.roomRecycler.adapter = recycler
        viewModel.loadContent()
        viewModel.contentState.observe(viewLifecycleOwner){
            renderScreen(it)
        }
        viewModel.hotelName.observe(viewLifecycleOwner){
            binding.hotelName.text = it
        }

    }


    private fun renderScreen(information: RoomsViewModelState){
        when(information){
            is RoomsViewModelState.RoomRecyclerContent ->{
                recycler.submitList(information.rooms)
            }
            else -> {}
        }
    }



}