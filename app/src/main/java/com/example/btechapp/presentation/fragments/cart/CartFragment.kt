package com.example.btechapp.presentation.fragments.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.btechapp.R
import com.example.btechapp.databinding.FragmentCartBinding
import com.example.btechapp.domain.productModel.ProductModel
import com.example.btechapp.presentation.states.UIState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment : Fragment() {

    private val viewModel: CartViewModel by viewModel()
    private lateinit var adapter: CartAdapter
    private val binding by viewBinding(FragmentCartBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_cart, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        observe()
    }

    private fun initialize() {
        binding.recyclerViewCart.layoutManager = LinearLayoutManager(requireContext())
        adapter = CartAdapter()

        binding.recyclerViewCart.adapter = adapter
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.getAllProducts.collectLatest { state ->
                when (state) {
                    is UIState.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is UIState.Empty -> {
                        Toast.makeText(requireContext(), "Ничего нет", Toast.LENGTH_LONG).show()
                        binding.progressBar.isVisible = false
                    }
                    is UIState.Error -> {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                        binding.progressBar.isVisible = false
                    }
                    is UIState.Success -> {
                        showProducts(state.data)
                        binding.progressBar.isVisible = false
                    }
                }
            }
        }
        viewModel.getAllProducts()
    }

    fun showProducts(products: List<ProductModel>) {
        val vertical = products
            .map {
                ProductModel(
                    image = it.image,
                    title = it.title,
                    price = it.price,
                    category = it.category,
                    description = it.description,
                    id = it.id
                )
            }
        adapter.setContent(vertical)
    }

}