package com.example.hoteltest.presentation.hotel

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.example.hoteltest.R
import com.example.hoteltest.databinding.FragmentHotelScreenBinding
import com.google.android.material.chip.Chip

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotelScreen : Fragment() {

    private var binding: FragmentHotelScreenBinding? = null
    private val viewModel by viewModels<HotelViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadContent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHotelScreenBinding.inflate(inflater,container,false)
        val list:List<Int>  = listOf(R.mipmap.first,R.mipmap.second,R.mipmap.third)
        val adapter = HotelViewPagerAdapter(list)
        binding?.viewPager2?.adapter = adapter
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.contentState.observe(viewLifecycleOwner){
            render(it)
        }
    }

    private fun render(information:HotelViewModelState){
        when (information) {
            is HotelViewModelState.HotelScreenContent -> {
                binding?.hotelName?.text = information.name
                binding?.hotelAdress?.text = information.adress
                binding?.minimalPrice?.text = information.minimalPrice.toString()
                binding?.hotelRating?.text = information.ratingNumName
                binding?.hotelAbout?.text = information.description
                for(peculiaritie in information.peculiarities){
                    addChip(peculiaritie)
                }
            }
            is HotelViewModelState.Error ->{

            }

            else -> {}
        }
    }
    private fun addChip(text:String){
        val chip = Chip(this.context,null, com.google.android.material.R.style.Widget_MaterialComponents_Chip_Choice)
        chip.shapeAppearanceModel = chip.shapeAppearanceModel.toBuilder().setAllCornerSizes(15.dp.value).build()
        chip.setChipBackgroundColorResource(R.color.light_grey)
        chip.setTextColor(resources.getColor(R.color.standard_grey,null))
        chip.text = text
        binding?.chipGroup?.addView(chip)
    }
}