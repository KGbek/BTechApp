package com.example.btech.presentation.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.btech.databinding.ItemHorizontalBinding
import com.example.btech.presentation.ui.models.HorizontalModel

class HorizontalAdapter(
    private val onItemClick: OnItemClick
) :
    RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {

    private val horizontalModels = mutableListOf<HorizontalModel>()

    fun setContent(model: List<HorizontalModel>) {
        horizontalModels.clear()
        horizontalModels.addAll(model)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindHorizontal(horizontalModels[position])
        holder.itemView.setOnClickListener {
            onItemClick.clickListener(horizontalModels[position])
        }
    }

    override fun getItemCount(): Int {
        return horizontalModels.size
    }

    class ViewHolder(private val binding: ItemHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindHorizontal(horizontalModel: HorizontalModel) {
            binding.imageViewPicture.load(horizontalModel.image)
            binding.textViewCategoery.text = horizontalModel.category

        }
    }

    interface OnItemClick {
        fun clickListener(horizontalModel: HorizontalModel)
    }
}