package com.example.hoteltest.presentation.hotel

import android.graphics.Bitmap
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.example.hoteltest.R
import com.example.hoteltest.databinding.FragmentHotelScreenBinding
import com.example.hoteltest.presentation.rooms.RoomsScreen
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
        binding = FragmentHotelScreenBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.contentState.observe(viewLifecycleOwner) {
            renderScreen(it)
        }
        binding?.roomsButton?.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.mainContainer,RoomsScreen()).addToBackStack("Rooms").commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun renderScreen(information: HotelViewModelState) {
        when (information) {
            is HotelViewModelState.HotelScreenContent -> {
                binding?.progressLayout?.visibility = View.GONE
                binding?.mainContent?.visibility = View.VISIBLE
                binding?.hotelName?.text = information.name
                binding?.hotelAdress?.text = information.adress
                binding?.minimalPrice?.text = information.minimalPrice.toString()
                binding?.hotelRating?.text = information.ratingNumName
                binding?.hotelAbout?.text = information.description
                for (peculiaritie in information.peculiarities) {
                    binding?.chipGroup?.addView(createChip(peculiaritie))
                }
                binding?.viewPager2?.adapter = ViewPagerAdapter(information.imageList)
            }

            is HotelViewModelState.Error -> {
                binding?.mainContent?.visibility = View.GONE
                binding?.progressLayout?.visibility = View.VISIBLE
                binding?.error?.visibility = View.VISIBLE
                binding?.progressCircle?.visibility = View.GONE
                binding?.error?.text = information.massage
            }

            is HotelViewModelState.Loading -> {
                binding?.mainContent?.visibility = View.GONE
                binding?.error?.visibility = View.GONE
                binding?.progressLayout?.visibility = View.VISIBLE
                binding?.progressCircle?.visibility = View.VISIBLE
            }

            else -> {}
        }
    }

    private fun createChip(text: String): Chip {
        val chip = Chip(
            this.context,
            null,
            com.google.android.material.R.style.Widget_MaterialComponents_Chip_Choice
        )
        chip.shapeAppearanceModel = chip.shapeAppearanceModel.toBuilder().setAllCornerSizes(15.dp.value).build()
        chip.setChipBackgroundColorResource(R.color.light_grey)
        chip.setTextColor(resources.getColor(R.color.standard_grey, null))
        chip.typeface = Typeface.createFromAsset(context?.assets, "font/sfprodisplayregular.otf")
        chip.text = text
        return chip
    }
}