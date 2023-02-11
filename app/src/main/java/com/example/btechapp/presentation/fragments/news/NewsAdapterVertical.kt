package com.example.btechapp.presentation.fragments.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.btechapp.R
import com.example.btechapp.databinding.ItemVerticalBinding
import com.example.btechapp.presentation.fragments.models.VerticalModel

class NewsAdapterVertical(
    private val onItemClicked: OnItemClicked
) :
    RecyclerView.Adapter<NewsAdapterVertical.ViewHolder>() {

    private val verticalModels = mutableListOf<VerticalModel>()

    fun setContent(modelVertical: List<VerticalModel>) {
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
            onItemClicked.clickListener(verticalModels[position])
        }
    }


    override fun getItemCount(): Int {
        return verticalModels.size
    }

    class ViewHolder(private val binding: ItemVerticalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindVertical(verticalModel: VerticalModel) {
            binding.imageView.load(verticalModel.image)
            binding.imageViewLike.setImageResource(R.drawable.ic_like)
            binding.textViewCredit.text = verticalModel.credit
            binding.textViewModel.text = verticalModel.model
            binding.textViewCost.text = verticalModel.cost

        }
    }

    interface OnItemClicked {
        fun clickListener(verticalModel: VerticalModel)
    }
}