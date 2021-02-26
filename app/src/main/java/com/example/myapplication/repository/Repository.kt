package com.example.myapplication.repository

import com.example.myapplication.Service
import com.example.myapplication.model.CepResponse
import retrofit2.Response

class Repository(private val service: Service) {

    suspend fun getCepService( cep: String) : Response<CepResponse> {
        return service.getEnderecoService(cep)
    }
}