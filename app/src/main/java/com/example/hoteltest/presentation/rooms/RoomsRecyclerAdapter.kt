package com.example.hoteltest.presentation.rooms

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.unit.dp
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hoteltest.databinding.RoomsRecyclerItemBinding
import com.example.hoteltest.presentation.hotel.ViewPagerAdapter
import com.example.hoteltest.presentation.reservation.ReservationScreen
import com.google.android.material.R
import com.google.android.material.chip.Chip

class RoomsInformationAdapter(private val context: Context?,private val openReservationLambda:(Int) -> Unit) : ListAdapter<RoomsRecyclerItemData, RoomHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomHolder =
        RoomHolder.createList(parent)

    override fun onBindViewHolder(holder: RoomHolder, position: Int) {
        holder.drawItem(getItem(position), context,openReservationLambda)
        onViewRecycled(holder)
    }

}
class RoomHolder(private val binding: RoomsRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){
    companion object {
        fun createList(list: ViewGroup): RoomHolder = RoomHolder(RoomsRecyclerItemBinding.inflate((LayoutInflater.from(list.context)), list, false))
    }
    fun drawItem(roomInfo: RoomsRecyclerItemData, context: Context?, openReservationScreen: (Int) -> Unit) {
        binding.roomName.text = roomInfo.name
        binding.viewPager2.adapter = ViewPagerAdapter(roomInfo.images)
        binding.viewPager2.let{ binding.dots.attachTo(it) }
        binding.minimalPrice.text = roomInfo.price.toString()
        binding.priceFor.text = roomInfo.pricePer
        roomInfo.peculiarities?.forEach{
            binding.chipGroup.addView(createChip(it,context))
        }
        binding.buttonToReservation.setOnClickListener {
            openReservationScreen(adapterPosition)
        }

    }

    private fun createChip(text: String, context: Context?): Chip {
        val chip = Chip(context, null, R.style.Widget_MaterialComponents_Chip_Choice)
        chip.shapeAppearanceModel = chip.shapeAppearanceModel.toBuilder().setAllCornerSizes(15.dp.value).build()
        chip.setChipBackgroundColorResource(com.example.hoteltest.R.color.light_grey)
        context?.resources?.let { chip.setTextColor(it.getColor(com.example.hoteltest.R.color.standard_grey, null)) }
        chip.typeface = Typeface.createFromAsset(context?.assets, "font/sfprodisplayregular.otf")
        chip.text = text
        return chip
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