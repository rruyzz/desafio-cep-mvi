package com.example.myapplication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.CepResponse
import kotlinx.coroutines.launch

class MainView(val service: Service) : ViewModel() {

    val adress = MutableLiveData<CepResponse>()

    fun getEndereco(cep: String): MutableLiveData<CepResponse> {
        viewModelScope.launch {
            try{
                adress.value = service.getEnderecoService(cep)
            }catch (exception: Exception) {
                Log.e("TAG", exception.message.toString())
            }
        }
        return adress
    }
}