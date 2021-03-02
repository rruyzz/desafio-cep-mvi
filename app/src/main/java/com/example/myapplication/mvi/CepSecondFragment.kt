package com.example.myapplication.mvi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.SecondFragmentDirections
import kotlinx.android.synthetic.main.fragment_cep_second.*


class CepSecondFragment : Fragment() {

    private val currentAddress: CepSecondFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cep_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        putViews()
    }

    private fun putViews() {
        val endereco = currentAddress.address
        tvBairroMvi.text = endereco.bairro
        tvCepMvi.text = endereco.cep
        tvLogadouroMvi.text = endereco.logradouro
    }

}