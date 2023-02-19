package com.example.btechapp.presentation.fragments.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.btechapp.R
import com.example.btechapp.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private lateinit var viewModel: CartViewModel
    private lateinit var adapter: CartAdapter
    private val binding by viewBinding(FragmentCartBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_cart, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewCart.layoutManager = LinearLayoutManager(requireContext())
        adapter = CartAdapter()

        binding.recyclerViewCart.adapter = adapter
    }

}