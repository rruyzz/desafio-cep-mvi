package com.example.myapplication.network

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import retrofit2.Response
import java.lang.reflect.Type
import java.net.UnknownHostException

object RetrofitRequest : KoinComponent {

    val type: Type = object : TypeToken<ErrorResponse>() {}.type

    suspend fun <T> doRetrofitRequest(
        requestMethod: String,
        call: suspend () -> Response<T>?
    ): RetrofitTreatedRequest<T> {
        return try {
            val response = withContext(Dispatchers.IO) { call.invoke() }
            when (response?.code()) {
                in 200..299 -> RetrofitTreatedRequest(response = response?.body(), isSuccess = true)
                in 400..401 -> {
                    val errorResponse: ErrorResponse? = Gson().fromJson(response?.errorBody()!!.charStream(), type)
                    RetrofitTreatedRequest(
                        hasError = true,
                        message = errorResponse?.message ?: UNEXPECTED_ERROR
                    )
                }
                else -> {
                    RetrofitTreatedRequest(
                        hasError = true,
                        message = UNEXPECTED_ERROR
                    )
                }
            }
        } catch (e: UnknownHostException) {
            RetrofitTreatedRequest(hasError = true, message = UNEXPECTED_ERROR)
        }
    }

    data class RetrofitTreatedRequest<T>(
        val response: T? = null,
        val hasError: Boolean = false,
        val isSuccess: Boolean = false,
        val message: String? = null
    )

    data class ErrorResponse(
        val status: Int,
        val message: String
    )

    val UNEXPECTED_ERROR = "Erro inesperado"
}