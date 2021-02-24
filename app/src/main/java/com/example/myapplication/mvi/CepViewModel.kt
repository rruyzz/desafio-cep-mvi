package com.example.myapplication.mvi

import androidx.lifecycle.LiveDataScope
import com.example.mvi.core.currentState

open class CepViewModel(dispatcher: CepDispatcher) : CepMVIViewModel(dispatcher) {
//    override suspend fun LiveDataScope<CepResults>.handleAction(action: CepActions) {
//        TODO("Not yet implemented")
//    }

    override fun reduce(result: CepResults): CepStates =
        CepReducer.reduce(currentState, result)
}