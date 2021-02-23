package com.example.myapplication.service

import com.example.myapplication.model.Endereco
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {

    @GET("{cep}/json")
    suspend fun getCepService( @Path("cep") cep: String) : Response<Endereco>

}