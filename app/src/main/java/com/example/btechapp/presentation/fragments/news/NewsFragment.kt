package com.example.btechapp.presentation.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.btechapp.R
import com.example.btechapp.databinding.FragmentNewsBinding
import com.example.btechapp.presentation.fragments.home.adapters.HorizontalAdapter
import com.example.btechapp.presentation.fragments.home.adapters.VerticalAdapter
import com.example.btechapp.presentation.fragments.models.VerticalModel

class NewsFragment : Fragment(), NewsAdapterVertical.OnItemClicked {

    private lateinit var adapterHorizontal: NewsAdapterHorizontal
    private lateinit var adapterVertical: NewsAdapterVertical
    private val binding by viewBinding(FragmentNewsBinding::bind)
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerNewsHorizontal.layoutManager = LinearLayoutManager(requireContext())
        adapterHorizontal = NewsAdapterHorizontal()

        binding.recyclerNewsVertical.layoutManager = LinearLayoutManager(requireContext())
        adapterVertical = NewsAdapterVertical(this)

        binding.recyclerNewsHorizontal.adapter = adapterHorizontal
        binding.recyclerNewsVertical.adapter = adapterVertical
    }

    override fun clickListener(verticalModel: VerticalModel) {
        val bundle = Bundle()
        bundle.putSerializable("key", verticalModel)
        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
    }

}