package com.example.myapplication.mvi

object CepReducer {

    fun reduce(currentState: CepStates, results: CepResults): CepStates{
        return when(results){
            CepResults.Loading -> currentState.copy(stateType = StateType.Loading)
            CepResults.SessionExpired -> currentState.copy(stateType = StateType.SessionExpired)
            is CepResults.SuccessCep -> currentState.copy(stateType = StateType.SuccessCep)
            is CepResults.ErrorCep -> currentState.copy(stateType = StateType.ErrorCep)
        }
    }
}