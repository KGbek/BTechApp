package com.example.btech.presentation.ui.details.viewPagerAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.btech.databinding.ItemViewPagerBinding
import com.example.btech.presentation.ui.details.viewPagerModel.ViewPagerModel

class ViewPagerAdapter :
    RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    val data = mutableListOf<ViewPagerModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemViewPagerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ItemViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewPagerModel: ViewPagerModel) {
            binding.imageViewPager.load(viewPagerModel.image)
        }
    }
}
