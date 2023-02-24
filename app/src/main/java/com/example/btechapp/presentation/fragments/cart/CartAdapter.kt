package com.example.btechapp.presentation.fragments.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.btechapp.R
import com.example.btechapp.databinding.ItemCartBinding
import com.example.btechapp.domain.productModel.ProductModel

class CartAdapter : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private val cartModel = mutableListOf<ProductModel>()

    fun setContent(modelCart: List<ProductModel>) {
        cartModel.clear()
        cartModel.addAll(modelCart)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        val binding =
            ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        holder.onBindCart(cartModel[position])
    }

    override fun getItemCount(): Int {
        return cartModel.size
    }

    class ViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindCart(cartModel: ProductModel) {
            binding.imageViewCart.load(cartModel.image)
            binding.imageViewArrow.setImageResource(R.drawable.ic_arrow_drop_up)
            binding.textViewLot.text = cartModel.title
            binding.textViewLotCost.text = cartModel.price
            binding.textViewColor.text = cartModel.description

        }
    }
}