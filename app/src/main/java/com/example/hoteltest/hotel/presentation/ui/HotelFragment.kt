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
            viewModel.openRoomsFragment()
        }
    }

    private fun renderScreen(state: HotelViewModelState) {
        when (state) {
            is HotelViewModelState.HotelFragmentContent -> showHotelInformation(state)

            is HotelViewModelState.Error -> showError(state)

            is HotelViewModelState.Loading -> showLoading()

            else -> {}
        }
    }

    private fun showHotelInformation(state: HotelViewModelState.HotelFragmentContent){
        binding.progressLayout.visibility = View.GONE
        binding.mainContent.visibility = View.VISIBLE
        binding.hotelName.text = state.name
        binding.hotelAdress.text = state.adress
        binding.minimalPrice.text = String.format(resources.getString(R.string.ruble_price_of),state.minimalPrice)
        binding.hotelRating.text = state.ratingNumName
        binding.hotelAbout.text = state.description
        binding.viewPager2.adapter = ViewPagerAdapter(state.imageList)
        binding.viewPager2.let { binding.dots.attachTo(it) }
        binding.priceFor.text = state.priceForIt
        state.peculiarities.forEach {
            binding.chipGroup.addView(createChip(it))}
    }

    private fun showLoading(){
        binding.mainContent.visibility = View.GONE
        binding.error.visibility = View.GONE
        binding.progressLayout.visibility = View.VISIBLE
        binding.progressCircle.visibility = View.VISIBLE
    }

    private fun showError(state:HotelViewModelState.Error){
        binding.mainContent.visibility = View.GONE
        binding.progressLayout.visibility = View.VISIBLE
        binding.error.visibility = View.VISIBLE
        binding.progressCircle.visibility = View.GONE
        binding.error.text = state.massage
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