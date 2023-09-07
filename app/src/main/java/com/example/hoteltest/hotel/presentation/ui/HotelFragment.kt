package com.example.hoteltest.hotel.presentation.ui

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.example.hoteltest.R
import com.example.hoteltest.binding.BaseFragment
import com.example.hoteltest.databinding.FragmentHotelFragmentBinding
import com.example.hoteltest.hotel.presentation.vm.HotelViewModel
import com.example.hoteltest.hotel.presentation.vm.HotelViewModelState
import com.google.android.material.chip.Chip

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotelFragment : BaseFragment<FragmentHotelFragmentBinding>() {

    private val viewModel by viewModels<HotelViewModel>()

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHotelFragmentBinding = FragmentHotelFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadContent()
        viewModel.contentState.observe(viewLifecycleOwner) {
            renderScreen(it)
        }
        binding.roomsButton.setOnClickListener {
            viewModel.openRoomScreen()
        }
    }


    private fun renderScreen(information: HotelViewModelState) {
        when (information) {
            is HotelViewModelState.HotelFragmentContent -> {
                binding.progressLayout.visibility = View.GONE
                binding.mainContent.visibility = View.VISIBLE
                binding.hotelName.text = information.name
                binding.hotelAdress.text = information.adress
                binding.minimalPrice.text = String.format(resources.getString(R.string.ruble_price_of),information.minimalPrice)
                binding.hotelRating.text = information.ratingNumName
                binding.hotelAbout.text = information.description
                binding.viewPager2.adapter = ViewPagerAdapter(information.imageList)
                binding.viewPager2.let { binding.dots.attachTo(it) }
                binding.priceFor.text = information.priceForIt
                information.peculiarities.forEach {
                    binding.chipGroup.addView(createChip(it))
                }
            }

            is HotelViewModelState.Error -> {
                binding.mainContent.visibility = View.GONE
                binding.progressLayout.visibility = View.VISIBLE
                binding.error.visibility = View.VISIBLE
                binding.progressCircle.visibility = View.GONE
                binding.error.text = information.massage
            }

            is HotelViewModelState.Loading -> {
                binding.mainContent.visibility = View.GONE
                binding.error.visibility = View.GONE
                binding.progressLayout.visibility = View.VISIBLE
                binding.progressCircle.visibility = View.VISIBLE
            }

            else -> {}
        }
    }

    private fun createChip(text: String): Chip {
        val chip = Chip(this.context, null, com.google.android.material.R.style.Widget_MaterialComponents_Chip_Choice)
        chip.shapeAppearanceModel = chip.shapeAppearanceModel.toBuilder().setAllCornerSizes(15.dp.value).build()
        chip.setChipBackgroundColorResource(R.color.light_grey)
        chip.setTextColor(resources.getColor(R.color.standard_grey, null))
        chip.typeface = Typeface.createFromAsset(context?.assets, "font/sfprodisplayregular.otf")
        chip.text = text
        return chip
    }

}