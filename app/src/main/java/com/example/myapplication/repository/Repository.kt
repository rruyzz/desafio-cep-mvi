package com.example.myapplication.repository

import com.example.myapplication.model.Endereco
import com.example.myapplication.service.Service
import retrofit2.Response
import retrofit2.http.Path

class Repository(private val service: Service) {

    suspend fun getCepService( cep: String) : Response<Endereco> {
        return service.getCepService(cep)
    }
}