package com.example.myapplication.network

import com.example.myapplication.Service
import retrofit2.Retrofit

fun provideService(retrofit: Retrofit) : Service{

    return retrofit.create(Service::class.java)

}