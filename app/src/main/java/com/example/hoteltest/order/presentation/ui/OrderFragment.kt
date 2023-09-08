package com.example.hoteltest.order.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hoteltest.R
import com.example.hoteltest.binding.BaseFragment
import com.example.hoteltest.databinding.FragmentOrderFragmentBinding
import com.example.hoteltest.order.presentation.vm.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

@AndroidEntryPoint
class OrderFragment : BaseFragment<FragmentOrderFragmentBinding>() {

    private val viewModel by viewModels<OrderViewModel>()

    companion object {
        const val ORDER_FRAGMENT_VALUE = "ORDER_VALUE"
        @JvmStatic
        fun newInstance(value: Serializable): OrderFragment {
            val transitValue: Bundle = Bundle().apply { putSerializable(ORDER_FRAGMENT_VALUE, value) }
            val fragment = OrderFragment()
            fragment.arguments = transitValue
            return fragment
        }
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentOrderFragmentBinding = FragmentOrderFragmentBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.orderData.observe(viewLifecycleOwner){
            binding.orderDescription.text = String.format(resources.getString(R.string.order_description,it.hashCode()))
        }
        binding.superButton.setOnClickListener {
            viewModel.openHotelFragment()
        }
    }
}