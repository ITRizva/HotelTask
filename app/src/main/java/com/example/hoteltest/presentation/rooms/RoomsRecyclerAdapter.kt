package com.example.hoteltest.presentation.rooms

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hoteltest.databinding.RoomsRecyclerItemBinding
import com.example.hoteltest.domain.Models.SingleRoomInformation
import com.example.hoteltest.presentation.hotel.ViewPagerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.launch

class RoomsInformationAdapter() : ListAdapter<RoomsRecyclerItemData, RoomHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomHolder =
        RoomHolder.createList(parent)

    override fun onBindViewHolder(holder: RoomHolder, position: Int) {
        holder.drawItem(getItem(position))
        onViewRecycled(holder)
    }

}
class RoomHolder(private val binding: RoomsRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){
    companion object {
        fun createList(list: ViewGroup): RoomHolder = RoomHolder(RoomsRecyclerItemBinding.inflate((LayoutInflater.from(list.context)), list, false))
    }
    fun drawItem(roomInfo: RoomsRecyclerItemData) {
        binding.roomName.text = roomInfo.name
        binding.viewPager2.adapter = ViewPagerAdapter(roomInfo.images)
    }
}

val diffCallback = object : DiffUtil.ItemCallback<RoomsRecyclerItemData>() {
    override fun areContentsTheSame(
        oldItem: RoomsRecyclerItemData,
        newItem: RoomsRecyclerItemData
    ): Boolean = oldItem == newItem


    override fun areItemsTheSame(
        oldItem: RoomsRecyclerItemData,
        newItem: RoomsRecyclerItemData
    ): Boolean = oldItem == newItem
}