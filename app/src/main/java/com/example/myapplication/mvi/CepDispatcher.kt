package com.example.myapplication.mvi

import androidx.lifecycle.LiveDataScope

import com.example.myapplication.repository.Repository

class CepDispatcher(private val repository: Repository): CepMVIDispatcher()  {
    override suspend fun LiveDataScope<CepResults>.handleAction(action: CepActions) {
        when (action) {
            is CepActions.CepRequestAction -> {
                emit(CepResults.Loading)
                val result = repository.getEnderecoService(action.cep)

//                when{
//                    result.hasError->emit(CepResults.ErrorCep(result.message!!))
//                    result.isSessionExpired -> emit(CepResults.SessionExpired)
//                    else -> emit(CepResults.SuccessCep(result.response!!))
//                }
            }
        }
    }
}