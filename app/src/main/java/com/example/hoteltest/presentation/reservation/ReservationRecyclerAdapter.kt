package com.example.hoteltest.presentation.reservation

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hoteltest.databinding.PersonsRecyclerItemBinding
import com.example.hoteltest.databinding.RoomsRecyclerItemBinding
import com.example.hoteltest.presentation.hotel.ViewPagerAdapter
import com.example.hoteltest.presentation.rooms.RoomsRecyclerItemData
import com.google.android.material.R
import com.google.android.material.chip.Chip

class ReservationRecyclerAdapter() : ListAdapter<PersonRecyclerItem, PersonHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder =
        PersonHolder.createList(parent)

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        holder.drawItem(getItem(position))
        onViewRecycled(holder)
    }

}
class PersonHolder(private val binding: PersonsRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){
    companion object {
        fun createList(list: ViewGroup): PersonHolder = PersonHolder(PersonsRecyclerItemBinding.inflate((LayoutInflater.from(list.context)), list, false))
    }
    fun drawItem(personInfo: PersonRecyclerItem) {

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