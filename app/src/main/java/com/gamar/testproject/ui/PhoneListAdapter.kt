package com.gamar.testproject.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gamar.testproject.databinding.PhoneItemBinding
import com.gamar.testproject.model.PhoneModel

class PhoneListAdapter(private var list: List<PhoneModel>) :
    RecyclerView.Adapter<PhoneListAdapter.PhoneViewHolder>() {
    class PhoneViewHolder(private val binding: PhoneItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(phoneModel: PhoneModel) {
            binding.name.text = "${phoneModel.id.toString()}. ${phoneModel.name}"
            binding.color.text = "Color: ${phoneModel.data?.color.orEmpty()}"
            binding.capacity.text = "Capacity: ${phoneModel.data?.capacity.orEmpty()}"
            binding.price.text = "Price: ${phoneModel.data?.price?.toString() ?: ""}$"

            binding.name.visibility = if (phoneModel.name.isNullOrEmpty()) View.GONE else View.VISIBLE
            binding.price.visibility = if (phoneModel.data?.price == null) View.GONE else View.VISIBLE
            binding.color.visibility = if (phoneModel.data?.color.isNullOrEmpty()) View.GONE else View.VISIBLE
            binding.capacity.visibility = if (phoneModel.data?.capacity.isNullOrEmpty()) View.GONE else View.VISIBLE
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhoneViewHolder {
        return PhoneViewHolder(
            PhoneItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(newPhoneList: List<PhoneModel>) {
        list = newPhoneList
        notifyDataSetChanged()
    }
}