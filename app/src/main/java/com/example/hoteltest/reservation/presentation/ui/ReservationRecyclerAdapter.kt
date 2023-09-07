package com.example.hoteltest.reservation.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hoteltest.databinding.PersonsRecyclerItemBinding

class ReservationRecyclerAdapter(private val personLambda: (Int, PersonRecyclerItem) -> Unit) :
    ListAdapter<PersonRecyclerItem, PersonHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder =
        PersonHolder.createList(parent)

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        holder.drawItem(getItem(position), personLambda)
        onViewRecycled(holder)
    }

}

class PersonHolder(private val binding: PersonsRecyclerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun createList(list: ViewGroup): PersonHolder = PersonHolder(
            PersonsRecyclerItemBinding.inflate(
                (LayoutInflater.from(list.context)),
                list,
                false
            )
        )
    }

    fun drawItem(personInfo: PersonRecyclerItem, personLambda: (Int, PersonRecyclerItem) -> Unit) {
        binding.numLabel.text = personInfo.itemLabel
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


    }

    private fun sendAllEditText(
        binding: PersonsRecyclerItemBinding,
        personLambda: (Int, PersonRecyclerItem) -> Unit,
        position: Int
    ) {
        val personData = PersonRecyclerItem(
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

val diffCallback = object : DiffUtil.ItemCallback<PersonRecyclerItem>() {
    override fun areContentsTheSame(
        oldItem: PersonRecyclerItem,
        newItem: PersonRecyclerItem
    ): Boolean = oldItem == newItem


    override fun areItemsTheSame(
        oldItem: PersonRecyclerItem,
        newItem: PersonRecyclerItem
    ): Boolean = oldItem == newItem
}