package com.example.btechapp.presentation.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.btechapp.presentation.fragments.home.adapters.HorizontalAdapter
import com.example.btechapp.presentation.fragments.home.adapters.VerticalAdapter
import com.example.btechapp.presentation.fragments.models.VerticalModel
import com.example.btechapp.R
import com.example.btechapp.databinding.FragmentHomeBinding
import com.example.btechapp.domain.productModel.ProductModel
import com.example.btechapp.presentation.fragments.models.HorizontalModel

class HomeFragment : Fragment(), HorizontalAdapter.OnItemClick, VerticalAdapter.OnItemClicked {

    private lateinit var adapterHorizontal: HorizontalAdapter
    private lateinit var adapterVertical: VerticalAdapter
    private lateinit var viewModel: HomeViewModel
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewHorizontal.layoutManager = LinearLayoutManager(requireContext())
        adapterHorizontal = HorizontalAdapter(this)

        binding.recyclerViewVertical.layoutManager = LinearLayoutManager(requireContext())
        adapterVertical = VerticalAdapter(this)

        binding.recyclerViewHorizontal.adapter = adapterHorizontal
        binding.recyclerViewVertical.adapter = adapterVertical

        viewModel.getAllProducts()
    }

    fun showProducts(products: List<VerticalModel>, productos: List<HorizontalModel>){
        adapterVertical.setContent(products)
        adapterHorizontal.setContent(productos)
    }

    override fun clickListener(horizontalModel: HorizontalModel) {
        val bundle = Bundle()
        bundle.putSerializable("key", horizontalModel)
        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
    }

    override fun clickListener(verticalModel: VerticalModel) {
        val bundle = Bundle()
        bundle.putSerializable("key", verticalModel)
        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
    }

}