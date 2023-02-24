package com.example.btechapp.presentation.fragments.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.btechapp.databinding.ItemHorizontalBinding
import com.example.btechapp.domain.productModel.ProductModel

class HorizontalAdapter(
    private val onItemClick: OnItemClick
) :
    RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {

    private val horizontalModels = mutableListOf<ProductModel>()

    fun setContent(model: List<ProductModel>) {
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
            onItemClick.horizontalClickListener(horizontalModels[position])
        }
    }

    override fun getItemCount(): Int {
        return horizontalModels.size
    }

    class ViewHolder(private val binding: ItemHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindHorizontal(model: ProductModel) {
            binding.imageViewPicture.load(model.image)
            binding.textViewCategoery.text = model.category

        }
    }

    interface OnItemClick {
        fun horizontalClickListener(model: ProductModel)
    }
}