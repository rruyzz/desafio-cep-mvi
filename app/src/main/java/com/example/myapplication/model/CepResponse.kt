package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
@Parcelize
data class CepResponse(
    var cep: String,
    var logradouro: String,
    var complemento: String,
    var bairro: String,
    var erro: Boolean,
    var localidade: String,
    var uf: String,
    var ibge: Int,
    var gia: Int,
    var ddd: Int,
    var siafi: Int
) : Parcelable