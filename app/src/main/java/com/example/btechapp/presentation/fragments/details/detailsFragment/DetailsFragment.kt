package com.example.btechapp.presentation.fragments.details.detailsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.btechapp.databinding.FragmentDetailsBinding
import com.example.btechapp.domain.productModel.ProductModel

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProductsDetails()
    }

    private fun showProductsDetails() {
        val productDetail = arguments?.getSerializable("key") as ProductModel
        with(binding){
            textViewModel.text = productDetail.title
            textViewColor.text= productDetail.description
            imageViewLot.load(productDetail.image)
            textViewReviews.text= productDetail.category
            textViewMemory.text= productDetail.price
        }
    }
}


