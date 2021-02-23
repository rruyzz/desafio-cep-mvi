package com.example.myapplication.mvi


@MVIState
data class AddressRegisterStates(
    val stateType: StateType? = null,
    val message: String = "",
    val successPostalCode: PostalCodeResponse? = null,
    val successClientResponse: ClientResponse? = null
)