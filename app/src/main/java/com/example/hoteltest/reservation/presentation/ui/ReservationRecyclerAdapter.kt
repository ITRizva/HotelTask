package com.example.hoteltest.reservation.presentation.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hoteltest.R
import com.example.hoteltest.databinding.PersonsRecyclerItemBinding
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher


class ReservationRecyclerAdapter(private val context:Context?,private val personLambda: (Int, PersonRegistrationItem) -> Unit) :
    ListAdapter<PersonRegistrationItem, PersonHolder>(diffCallback) {

    private var currentViewList:PersonHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder = PersonHolder.createList(parent)

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        holder.drawItem(context,getItem(position), personLambda)
        onViewRecycled(holder)
    }
}

class PersonHolder(private val binding: PersonsRecyclerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun createList(list: ViewGroup): PersonHolder = PersonHolder(PersonsRecyclerItemBinding.inflate((LayoutInflater.from(list.context)), list, false))
    }

    fun drawItem(context: Context?,personInfo: PersonRegistrationItem, personLambda: (Int, PersonRegistrationItem) -> Unit) {
        binding.numLabel.text = personInfo.itemLabel
        //Восстанавливаю введенный текст заранее
        binding.nameEditText.setText(personInfo.name)
        binding.surnameEditText.setText(personInfo.surName)
        binding.bornDateEditText.setText(personInfo.bornDate)
        binding.citizenshipEditText.setText(personInfo.citizenShip)
        binding.intpassportEditText.setText(personInfo.numIntPassport)
        binding.durationIntpassEditText.setText(personInfo.durationIntPassport)

        setDateMasK(binding.bornDateEditText)
        setPassportNumMusk(binding.intpassportEditText)
        setValidPeriodPassportMask(binding.durationIntpassEditText)
        val clearBackground  = context?.resources?.getColor(R.color.grey_edittext,null)
        if (clearBackground != null) {
            clearBackgroundBox(clearBackground)
        }
        binding.personArrow.setOnClickListener {
            if (binding.personLayoutInfo.visibility == View.VISIBLE) {
                binding.personLayoutInfo.visibility = View.GONE
                it.rotation = 180f
            } else {
                binding.personLayoutInfo.visibility = View.VISIBLE
                it.rotation = 0f
            }
        }
        binding.nameEditText.addTextChangedListener {
            sendAllEditText(binding, personLambda, adapterPosition)
            if (clearBackground != null) {
                clearBackgroundBox(clearBackground)
            }
        }
        binding.surnameEditText.addTextChangedListener {
            sendAllEditText(binding, personLambda, adapterPosition)
            if (clearBackground != null) {
                clearBackgroundBox(clearBackground)
            }
        }
        binding.bornDateEditText.addTextChangedListener {
            sendAllEditText(binding, personLambda, adapterPosition)
            if (clearBackground != null) {
                clearBackgroundBox(clearBackground)
            }
        }
        binding.citizenshipEditText.addTextChangedListener {
            sendAllEditText(binding, personLambda, adapterPosition)
            if (clearBackground != null) {
                clearBackgroundBox(clearBackground)
            }
        }
        binding.intpassportEditText.addTextChangedListener {
            sendAllEditText(binding, personLambda, adapterPosition)
            if (clearBackground != null) {
                clearBackgroundBox(clearBackground)
            }
        }
        binding.durationIntpassEditText.addTextChangedListener {
            sendAllEditText(binding, personLambda, adapterPosition)
            if (clearBackground != null) {
                clearBackgroundBox(clearBackground)
            }
        }
        if(!personInfo.isCorrect){
            val errorBackground = context?.resources?.getColor(R.color.invalid_edit_text,null)
            if (errorBackground != null) {
                setErrorBackgroundBox(errorBackground)
            }
        }
    }

    private fun setDateMasK(editText: EditText){
        val slots = UnderscoreDigitSlotsParser().parseSlots("__.__.____")
        val formatWatcher: FormatWatcher = MaskFormatWatcher(MaskImpl.createTerminated(slots))
        formatWatcher.installOn(editText)
    }

    private fun setValidPeriodPassportMask(editText: EditText){
        val slots = UnderscoreDigitSlotsParser().parseSlots("__.__.____-__.__.____")
        val formatWatcher: FormatWatcher = MaskFormatWatcher(MaskImpl.createTerminated(slots))
        formatWatcher.installOn(editText)
    }

    private fun setPassportNumMusk(editText: EditText){
        val slots = UnderscoreDigitSlotsParser().parseSlots("___________")
        val formatWatcher: FormatWatcher = MaskFormatWatcher(MaskImpl.createTerminated(slots))
        formatWatcher.installOn(editText)
    }
    private fun setErrorBackgroundBox(errorBackground:Int){
            binding.nameEditTextLayout.boxBackgroundColor = errorBackground
            binding.surnameEditTextLayout.boxBackgroundColor = errorBackground
            binding.bornDateEditTextLayout.boxBackgroundColor = errorBackground
            binding.citizenshipEditTextLayout.boxBackgroundColor = errorBackground
            binding.intpassportEditTextLayout.boxBackgroundColor = errorBackground
            binding.durationIntpassEditTextLayout.boxBackgroundColor = errorBackground
    }

    private fun clearBackgroundBox(clearBackground:Int){
        binding.nameEditTextLayout.boxBackgroundColor = clearBackground
        binding.surnameEditTextLayout.boxBackgroundColor = clearBackground
        binding.bornDateEditTextLayout.boxBackgroundColor = clearBackground
        binding.citizenshipEditTextLayout.boxBackgroundColor = clearBackground
        binding.intpassportEditTextLayout.boxBackgroundColor = clearBackground
        binding.durationIntpassEditTextLayout.boxBackgroundColor = clearBackground
    }

    private fun sendAllEditText(
        binding: PersonsRecyclerItemBinding,
        personLambda: (Int, PersonRegistrationItem) -> Unit,
        position: Int
    ) {
        val personData = PersonRegistrationItem(
            itemLabel = binding.numLabel.text.toString(),
            name = binding.nameEditText.text.toString(),
            surName = binding.surnameEditText.text.toString(),
            bornDate = binding.bornDateEditText.text.toString(),
            citizenShip = binding.citizenshipEditText.text.toString(),
            numIntPassport = binding.intpassportEditText.text.toString(),
            durationIntPassport = binding.durationIntpassEditText.text.toString()
        )
        personLambda(position, personData)
    }
}

val diffCallback = object : DiffUtil.ItemCallback<PersonRegistrationItem>() {
    override fun areContentsTheSame(
        oldItem: PersonRegistrationItem,
        newItem: PersonRegistrationItem
    ): Boolean = oldItem == newItem


    override fun areItemsTheSame(
        oldItem: PersonRegistrationItem,
        newItem: PersonRegistrationItem
    ): Boolean = oldItem == newItem
}