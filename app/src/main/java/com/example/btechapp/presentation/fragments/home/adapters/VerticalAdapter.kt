package com.example.btechapp.presentation.fragments.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.btechapp.R
import com.example.btechapp.databinding.ItemVerticalBinding
import com.example.btechapp.domain.productModel.ProductModel

class VerticalAdapter(
    private val onItemClicked: OnItemClicked
) :
    RecyclerView.Adapter<VerticalAdapter.ViewHolder>() {

    private val verticalModels = mutableListOf<ProductModel>()

    fun setContent(modelVertical: List<ProductModel>) {
        verticalModels.clear()
        verticalModels.addAll(modelVertical)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindVertical(verticalModels[position])
        holder.itemView.setOnClickListener {
            onItemClicked.verticalClickListener(verticalModels[position])
        }
    }

    override fun getItemCount(): Int {
        return verticalModels.size
    }

    class ViewHolder(private val binding: ItemVerticalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindVertical(verticalModel: ProductModel) {
            binding.imageView.load(verticalModel.image)
            binding.imageViewLike.setImageResource(R.drawable.ic_like)
            binding.textViewCredit.text = verticalModel.description
            binding.textViewModel.text = verticalModel.title
            binding.textViewCost.text = verticalModel.price

        }
    }

    interface OnItemClicked {
        fun verticalClickListener(model: ProductModel)
    }
}