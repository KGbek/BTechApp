package com.example.btechapp.di

import com.example.btechapp.data.repository.ProductsRemoteRepositoty
import com.example.btechapp.domain.repository.ProductRepository
import com.example.btechapp.domain.use_case.GetAllProductsUseCase
import com.example.btechapp.presentation.fragments.cart.CartViewModel
import com.example.btechapp.presentation.fragments.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

object ProductModule {
    fun create() = module {
        single {
            ProductsRemoteRepositoty(get())
        } bind ProductRepository::class

        viewModel {
            HomeViewModel(get())
        }

        viewModel {
            CartViewModel(get())
        }

        factory {
            GetAllProductsUseCase(get())
        }

    }
}