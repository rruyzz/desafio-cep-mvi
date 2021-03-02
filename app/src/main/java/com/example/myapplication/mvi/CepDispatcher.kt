package com.example.myapplication.mvi

import androidx.lifecycle.LiveDataScope
import com.example.myapplication.network.RetrofitRequest

import com.example.myapplication.repository.Repository

class CepDispatcher(private val repository: Repository) : CepMVIDispatcher() {
    override suspend fun LiveDataScope<CepResults>.handleAction(action: CepActions) {
        when (action) {
            is CepActions.CepRequestAction -> {
                emit(CepResults.Loading)
                val result = RetrofitRequest.doRetrofitRequest("getCepService") {
                    repository.getCepService(action.cep)
                }
                when {
                    result.isSessionExpired -> emit(CepResults.SessionExpired)
                    result.hasError -> emit(CepResults.ErrorCep(result.message!!))
                    else -> emit(CepResults.SuccessCep(result.response!!))

//                        result.isSuccessful -> emit(CepResults.SuccessCep(result.body()!!))
//                    result.errorBody() -> emit(CepResults.ErrorCep())
                }
            }

        }


//                when{
//                    result.hasError->emit(CepResults.ErrorCep(result.message!!))
//                    result.isSessionExpired -> emit(CepResults.SessionExpired)
//                    else -> emit(CepResults.SuccessCep(result.response!!))
//                }

    }
}
