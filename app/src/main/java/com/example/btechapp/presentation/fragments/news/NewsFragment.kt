package com.example.btechapp.presentation.fragments.news

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
import com.example.btechapp.databinding.FragmentNewsBinding
import com.example.btechapp.domain.productModel.ProductModel
import com.example.btechapp.presentation.fragments.details.detailsFragment.DetailsFragment
import com.example.btechapp.presentation.fragments.home.HomeViewModel
import com.example.btechapp.presentation.fragments.models.HorizontalNewsModel
import com.example.btechapp.presentation.fragments.news.adapters.NewsAdapterHorizontal
import com.example.btechapp.presentation.fragments.news.adapters.NewsAdapterVertical
import com.example.btechapp.presentation.states.UIState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment(), NewsAdapterVertical.OnItemClicked {

    private lateinit var adapterHorizontal: NewsAdapterHorizontal
    private lateinit var adapterVertical: NewsAdapterVertical
    private val binding by viewBinding(FragmentNewsBinding::bind)
    private val viewModel: NewsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        observe()
    }

    private fun initialize() {
        binding.recyclerNewsHorizontal.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapterHorizontal = NewsAdapterHorizontal()

        binding.recyclerNewsVertical.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapterVertical = NewsAdapterVertical(this)

        binding.recyclerNewsHorizontal.adapter = adapterHorizontal
        binding.recyclerNewsVertical.adapter = adapterVertical
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
        adapterVertical.setContent(vertical)
    }

    fun setHorizontalContent(content: List<HorizontalNewsModel>){
        val horizontal = content
            .map {
                HorizontalNewsModel(
                    cartBackground1 = R.drawable.ad_image1_1,
                    cartBackground2 = R.drawable.ad_image1_2,
                    cartImage = R.drawable.ad_image1_3,
                    adText = R.string.sony
                )
            }
        adapterHorizontal.setContent(horizontal)
    }

    override fun clickListener(verticalModel: ProductModel) {
        val bundle = Bundle()
        bundle.putSerializable("key", verticalModel)
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