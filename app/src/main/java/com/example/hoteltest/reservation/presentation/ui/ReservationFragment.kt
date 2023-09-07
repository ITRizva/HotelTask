package com.example.hoteltest.reservation.presentation.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hoteltest.R
import com.example.hoteltest.binding.BaseFragment
import com.example.hoteltest.databinding.FragmentReservationFragmentBinding
import com.example.hoteltest.navigation.navigator
import com.example.hoteltest.reservation.presentation.vm.ReservationDataState

import com.example.hoteltest.reservation.presentation.vm.ReservationViewModel
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.MaskFormatWatcher
import java.io.Serializable


@AndroidEntryPoint
class ReservationFragment : BaseFragment<FragmentReservationFragmentBinding>() {

    private val viewModel by viewModels<ReservationViewModel>()

    private val recycler: ReservationRecyclerAdapter by lazy {
        ReservationRecyclerAdapter { position: Int, personData: PersonRegistrationItem ->
            viewModel.writePersonData(
                position,
                personData
            )
        }
    }

    companion object {
        const val RESERVATION_SCREEN_VALUE = "RESERVATION_VALUE"

        @JvmStatic
        fun newInstance(value: Serializable): ReservationFragment {
            val transitValue: Bundle =
                Bundle().apply { putSerializable(RESERVATION_SCREEN_VALUE, value) }
            val fragment = ReservationFragment()
            fragment.arguments = transitValue
            return fragment
        }
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReservationFragmentBinding =
        FragmentReservationFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMaskOnEditText(binding.phoneEditText)
        binding.recyclerPersons.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerPersons.adapter = recycler

        viewModel.reservationData.observe(viewLifecycleOwner) {
            recycler.submitList(it.personList)
        }
        viewModel.reservationData.observe(viewLifecycleOwner) {
            renderScreen(it)
        }
        binding.addPerson.setOnClickListener {
            viewModel.addPerson()
        }
        binding.emailEditText.addTextChangedListener {
            cleanEditTextBackground()
            viewModel.setEmail(it.toString())
        }
        binding.phoneEditText.addTextChangedListener {
            cleanEditTextBackground()
            viewModel.setPhone(it.toString())
        }
        binding.payButton.setOnClickListener {
            if (binding.emailEditText.text.toString().isEmailValid() && binding.phoneEditText.text.toString().trim().isPhoneValid()
            ) {
                viewModel.openOrderFragment(navigator())
            } else {
                Toast.makeText(activity, resources.getString(R.string.num_email_error), Toast.LENGTH_SHORT).show()
                setErrorEditTextBackground()
            }
        }
    }

    private fun renderScreen(state:ReservationDataState){
        binding.hotelRating.text = state.ratingNumName
        binding.hotelName.text = state.hotelname
        binding.hotel.text = state.hotelname
        binding.hotelAdress.text = state.adress
        binding.pricePer.text = state.pricePer
        binding.roomName.text = state.roomname
        binding.nutrition.text = state.peculiarities?.get(0)
        binding.tour.text = String.format(resources.getString(R.string.ruble_price),state.tour.toString())
        binding.fuelPrice.text = String.format(resources.getString(R.string.ruble_price),state.fuel.toString())
        binding.servicePrice.text = String.format(resources.getString(R.string.ruble_price),state.service.toString())
        binding.fullPrice.text = String.format(resources.getString(R.string.ruble_price),state.fullPrice.toString())
        binding.payButton.text = String.format(resources.getString(R.string.ruble_button_price),state.fullPrice.toString())
    }
    private fun setErrorEditTextBackground() {
        val errorBackground = resources.getColor(R.color.invalid_edit_text,null)
        binding.emailEditLayout.boxBackgroundColor = errorBackground
        binding.phoneEditLayout.boxBackgroundColor =  errorBackground
    }

    private fun cleanEditTextBackground() {
        val clearBackground  = resources.getColor(R.color.grey_edittext,null)
        binding.emailEditLayout.boxBackgroundColor = clearBackground
        binding.phoneEditLayout.boxBackgroundColor = clearBackground
    }

    private fun setMaskOnEditText(edittext: EditText) {
        val mask: MaskImpl = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
        mask.placeholder = '*'
        mask.isShowingEmptySlots = true
        val formatWatcher = MaskFormatWatcher(mask)
        formatWatcher.installOn(edittext)
        edittext.setText("+")
    }

    private fun String.isEmailValid(): Boolean = !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun String.isPhoneValid(): Boolean = Regex(""".\d..\d{3}..\d{3}-\d{2}-\d{2}""").matches(this)
}