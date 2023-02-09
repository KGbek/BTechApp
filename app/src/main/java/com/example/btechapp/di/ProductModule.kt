package com.example.btechapp.di

import com.example.btechapp.data.repository.RepositotyImpl
import org.koin.dsl.bind
import org.koin.dsl.module

object ProductModule {

    fun create() = module {
        single {
            RepositotyImpl(get())
        } bind RepositotyImpl::class

    }
}