//package com.example.myapplication.service
//
//import com.example.myapplication.model.CepResponse
//import retrofit2.Response
//import retrofit2.http.GET
//import retrofit2.http.Path
//
//interface Service {
//
//    @GET("{cep}/json")
//    suspend fun getCepService( @Path("cep") cep: String) : Response<CepResponse>
//
//}
//
//val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//val okHttp = OkHttpClient.Builder().addInterceptor(logger)
//val retrofitt = Retrofit.Builder()
//    .baseUrl("https://viacep.com.br/ws/")
//    .addConverterFactory(GsonConverterFactory.create())
//    .client(okHttp.build())
//    .build()
//
//val services: Service = retrofitt.create(Service::class.java)