//package com.example.myapplication.network
//
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.invoke
//import kotlinx.coroutines.withContext
//import org.koin.core.KoinComponent
//import retrofit2.Response
//import retrofit2.Retrofit
//import java.io.IOException
//import java.lang.Exception
//import java.net.UnknownHostException
//
//object RetrofitRequest : KoinComponent {
//
//    suspend fun<T> doRetrofitRequest(
//        requestMethod: String,
//        call: suspend () -> Response<T>?
//    ) : RetrofitTreatedRequest<T>{
//        return try{
//            val response = withContext(Dispatchers.IO) {call.invoke()}
//            when(response?.code()){
//                in 200..299 -> RetrofitTreatedRequest(response = response?.body(), isSuccess = true)
//                400 -> RetrofitTreatedRequest(hasError = true,  message = "erro 400")
//                401 -> RetrofitTreatedRequest(hasError = true, message = "erro 401")
//                else -> RetrofitTreatedRequest(hasError = true, message = "erro inesperado")
//            }
//
//        } catch (e: UnknownHostException){
//            RetrofitTreatedRequest(hasError = true, message = "UnknownHostException")
//        } catch (e: IOException){
//            RetrofitTreatedRequest(hasError = true, message = "IOException")
//        } catch (e: Exception){
//            RetrofitTreatedRequest(hasError = true, message = "Exception")
//        }
//    }
//
//    data class RetrofitTreatedRequest<T>(
//        val response: T? = null,
//        val isSessionExpired: Boolean = false,
//        val hasError: Boolean = false,
//        val isSuccess: Boolean = false,
//        val message: String? = null
//    )
//}