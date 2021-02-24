package com.example.myapplication.di

import com.example.myapplication.network.provideRetrofit
import com.example.myapplication.network.provideService
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

private val qualifierService = StringQualifier("qualifierService")

val serviceModule = module(override = true) {
    single { provideService(get(qualifierService)) }
    single {
        provideRetrofit(get())
    }
}