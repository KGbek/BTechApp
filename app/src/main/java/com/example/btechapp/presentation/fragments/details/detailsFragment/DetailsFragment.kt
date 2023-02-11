package com.example.btechapp.presentation.fragments.details.detailsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.btechapp.R
import com.example.btechapp.databinding.FragmentDetailsBinding
import com.example.btechapp.presentation.fragments.details.viewPager.viewPagerAdapter.ViewPagerAdapter

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var adapter: ViewPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewPager.adapter = adapter
    }
}