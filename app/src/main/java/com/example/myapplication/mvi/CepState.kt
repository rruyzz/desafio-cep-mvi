package com.example.myapplication.mvi


import com.example.mvi.core.MVIDispatcher
import com.example.mvi.core.MVIFragment
import com.example.mvi.core.MVIViewModel
import com.example.mvi_annotations.MVIActions
import com.example.mvi_annotations.MVIResults
import com.example.mvi_annotations.MVIState

import com.example.myapplication.model.CepResponse
import retrofit2.Response


@MVIState
data class CepStates(
    val stateType: StateType? = null,
    val message: String = "",
    val successCep: CepResponse? = null
)

@MVIActions
sealed class CepActions {
    data class CepRequestAction(val cep: String) : CepActions()
}

@MVIResults
sealed class CepResults {
    object Loading : CepResults()
    object SessionExpired : CepResults()

    data class SuccessCep(val successCepResult: CepResponse) : CepResults()
    data class ErrorCep(val message: String) : CepResults()

}

sealed class StateType {
    object Loading : StateType()
    object SessionExpired : StateType()
    object SuccessCep : StateType()
    object ErrorCep : StateType()
}

typealias CepMVIViewModel = MVIViewModel<CepActions, CepResults, CepStates>
typealias CepMVIDispatcher = MVIDispatcher<CepActions, CepResults>
typealias CepMVIFragment = MVIFragment<CepStates>