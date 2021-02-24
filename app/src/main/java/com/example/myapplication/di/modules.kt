package com.example.myapplication.di

import com.example.myapplication.mvi.CepDispatcher
import com.example.myapplication.mvi.CepViewModel
import com.example.myapplication.repository.Repository
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {

    viewModel { CepViewModel(get()) }
    single { Repository(get())}
    single{ CepDispatcher(get())}
}