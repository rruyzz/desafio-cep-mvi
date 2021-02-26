package com.example.myapplication.di

import com.example.myapplication.Service
import com.example.myapplication.mvi.CepDispatcher
import com.example.myapplication.mvi.CepViewModel
import com.example.myapplication.repository.Repository
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val module = module {
    //single <Service>{ provideRetrofit() }
    factory { Repository(provideRetrofit()) }
    factory{ CepDispatcher(get())}
    viewModel { CepViewModel(get()) }

}

fun provideRetrofit(): Service {
    val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val okHttp = OkHttpClient.Builder().addInterceptor(logger)
    val retrofit = Retrofit.Builder()
        .baseUrl("https://viacep.com.br/ws/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())
        .build()

    return retrofit.create(Service::class.java)
}