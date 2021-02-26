package com.example.myapplication.di

import com.example.myapplication.Service
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val qualifierService = StringQualifier("qualifierService")

//val networkModule = module {
//
//