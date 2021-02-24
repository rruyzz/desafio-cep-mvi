package com.example.myapplication.service

import com.example.myapplication.Service
import com.example.myapplication.model.Endereco
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {

    @GET("{cep}/json")
    suspend fun getCepService( @Path("cep") cep: String) : Response<Endereco>

}

//val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//val okHttp = OkHttpClient.Builder().addInterceptor(logger)
//val retrofitt = Retrofit.Builder()
//    .baseUrl("https://viacep.com.br/ws/")
//    .addConverterFactory(GsonConverterFactory.create())
//    .client(okHttp.build())
//    .build()
//
//val services: Service = retrofitt.create(Service::class.java)