package com.example.hoteltest.reservation.presentation.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hoteltest.R
import com.example.hoteltest.databinding.PersonsRecyclerItemBinding

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
        val clearBackground  = context?.resources?.getColor(R.color.grey_edittext,null)
        if (clearBackground != null) {
            binding.nameEditTextLayout.boxBackgroundColor = clearBackground
            binding.surnameEditTextLayout.boxBackgroundColor = clearBackground
            binding.bornDateEditTextLayout.boxBackgroundColor = clearBackground
            binding.citizenshipEditTextLayout.boxBackgroundColor = clearBackground
            binding.intpassportEditTextLayout.boxBackgroundColor = clearBackground
            binding.durationIntpassEditTextLayout.boxBackgroundColor = clearBackground
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
        }
        binding.surnameEditText.addTextChangedListener {
            sendAllEditText(binding, personLambda, adapterPosition)
        }
        binding.bornDateEditText.addTextChangedListener {
            sendAllEditText(binding, personLambda, adapterPosition)
        }
        binding.citizenshipEditText.addTextChangedListener {
            sendAllEditText(binding, personLambda, adapterPosition)
        }
        binding.intpassportEditText.addTextChangedListener {
            sendAllEditText(binding, personLambda, adapterPosition)
        }
        binding.durationIntpassEditText.addTextChangedListener {
            sendAllEditText(binding, personLambda, adapterPosition)
        }
        if(!personInfo.isCorrect){
            val errorBackground = context?.resources?.getColor(R.color.invalid_edit_text,null)
            if (errorBackground != null) {
                binding.nameEditTextLayout.boxBackgroundColor = errorBackground
                binding.surnameEditTextLayout.boxBackgroundColor = errorBackground
                binding.bornDateEditTextLayout.boxBackgroundColor = errorBackground
                binding.citizenshipEditTextLayout.boxBackgroundColor = errorBackground
                binding.intpassportEditTextLayout.boxBackgroundColor = errorBackground
                binding.durationIntpassEditTextLayout.boxBackgroundColor = errorBackground
            }
        }
    }

    private fun sendAllEditText(
        binding: PersonsRecyclerItemBinding,
        personLambda: (Int, PersonRegistrationItem) -> Unit,
        position: Int
    ) {
        val personData = PersonRegistrationItem(
            itemLabel = binding.numLabel.text.toString(),
            name = binding.nameEditText.text.toString(),
            surName = binding.surnameEditText.toString(),
            bornDate = binding.bornDateEditText.toString(),
            citizenShip = binding.citizenshipEditText.toString(),
            numIntPassport = binding.intpassportEditText.toString(),
            durationIntPassport = binding.durationIntpassEditText.toString()
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