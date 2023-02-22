package com.example.btechapp.presentation.fragments.home

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
import com.example.btechapp.presentation.fragments.home.adapters.HorizontalAdapter
import com.example.btechapp.presentation.fragments.home.adapters.VerticalAdapter
import com.example.btechapp.R
import com.example.btechapp.databinding.FragmentHomeBinding
import com.example.btechapp.domain.productModel.ProductModel
import com.example.btechapp.presentation.fragments.details.detailsFragment.DetailsFragment
import com.example.btechapp.presentation.states.UIState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), HorizontalAdapter.OnItemClick, VerticalAdapter.OnItemClicked {

    private lateinit var adapterHorizontal: HorizontalAdapter
    private lateinit var adapterVertical: VerticalAdapter
    private val viewModel: HomeViewModel by viewModel()
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        observe()
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

    private fun initialize() {
        binding.recyclerViewHorizontal.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapterHorizontal = HorizontalAdapter(this)

        binding.recyclerViewVertical.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapterVertical = VerticalAdapter(this)

        binding.recyclerViewHorizontal.adapter = adapterHorizontal
        binding.recyclerViewVertical.adapter = adapterVertical
    }

    fun showProducts(products: List<ProductModel>) {
        val vertical = products
            .filterIndexed { index, model ->
                index % 2 == 0
            }
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

        val horizontal = products
            .filterIndexed { index, model ->
                index % 2 != 0
            }
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
        adapterVertical.setContent(vertical)
        adapterHorizontal.setContent(horizontal)
    }

    override fun horizontalClickListener(model: ProductModel) {
        val bundle = Bundle()
        bundle.putSerializable("key", model)
        val fragment = DetailsFragment()
        fragment.arguments = bundle
        loadFragment(fragment)
    }

    override fun verticalClickListener(model: ProductModel) {
        val bundle = Bundle()
        bundle.putSerializable("key", model)
        val fragment = DetailsFragment()
        fragment.arguments = bundle
        loadFragment(fragment)
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

}