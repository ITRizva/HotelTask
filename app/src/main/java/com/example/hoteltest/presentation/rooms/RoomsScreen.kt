package com.example.hoteltest.presentation.rooms

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hoteltest.databinding.FragmentHotelScreenBinding
import com.example.hoteltest.databinding.FragmentRoomsScreenBinding
import com.example.hoteltest.databinding.RoomsRecyclerItemBinding
import com.google.android.material.R
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notify
import java.io.Serializable

@AndroidEntryPoint
class RoomsScreen : Fragment() {

    private var binding:FragmentRoomsScreenBinding? = null
    private val recycler:RoomsInformationAdapter by lazy{ RoomsInformationAdapter(context)}

    private val viewModel by viewModels<RoomsViewModel>()

    companion object{
        const val ROOM_SCREEN_VALUE = "STS_VALUE"
        @JvmStatic
        fun newInstance(value: Serializable): RoomsScreen {
            val transitValue: Bundle = Bundle().apply { putSerializable(ROOM_SCREEN_VALUE,value) }
            val fragment = RoomsScreen()
            fragment.arguments = transitValue
            return fragment
        }
    }

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
                }

                else -> {}

            }

        }

    }



}