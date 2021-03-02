package com.example.myapplication.mvi

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgsLazy
import androidx.navigation.fragment.findNavController
import com.example.mvi.UiStateMachine
import com.example.mvi.core.currentState
import com.example.myapplication.R
import com.example.myapplication.model.CepResponse
import com.example.myapplication.repository.Repository
import com.redmadrobot.inputmask.MaskedTextChangedListener
import kotlinx.android.synthetic.main.fragment_cep.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Response


class CepFragment : CepMVIFragment() {

    override val uiStateMachine: UiStateMachine<CepStates> get() = viewModel
    private val viewModel: CepViewModel by viewModel()
    lateinit var cep: String
    private val invalidCep = "Cep Invalido"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cep, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inputmvi.setCepMask()
        cep = inputmvi.text.toString()
        cep = unMask(cep)
        btnmvi.setOnClickListener {
            viewModel.mutate(
                CepActions.CepRequestAction(unMask(inputmvi.text.toString()))
            )
        }
    }

    override fun render(state: CepStates) {
        when (state.stateType) {
            is StateType.SuccessCep -> renderSucessCep(state)
            is StateType.Loading -> renderLoadState()
            else -> renderErroCep(state)
        }
    }

    private fun renderLoadState() {
        hideViews()
        showLoanding()
    }

    private fun renderSucessCep(state: CepStates) {
        hideLoanding()
        val response: CepResponse = state.successCep!!
        if (response != null && response.erro == false) {
            navigation(response)
        } else if(response.erro == true){
            invalidCep.toast()
            showViews()
        }
    }

    private fun renderErroCep(state: CepStates) {
        showViews()
        hideLoanding()
        state.message.toast()
    }

    private fun hideLoanding() {
        progressBarCep.visibility = View.INVISIBLE
    }

    private fun showLoanding() {
        progressBarCep.visibility = View.VISIBLE
    }

    private fun hideViews() {
        textInputLayoutMvi.visibility = View.INVISIBLE
        btnmvi.visibility = View.INVISIBLE
        inputmvi.visibility = View.INVISIBLE
    }

    private fun showViews() {
        textInputLayoutMvi.visibility = View.VISIBLE
        btnmvi.visibility = View.VISIBLE
        inputmvi.visibility = View.VISIBLE
    }

    private fun EditText.setCepMask() {
        val editText = this
        val listener = object : MaskedTextChangedListener("[00000]-[000]", editText) {
            override fun afterTextChanged(edit: Editable?) {
                try {
                    super.afterTextChanged(edit)
                } catch (e: Exception) {
                    editText.addTextChangedListener(this)
                    editText.onFocusChangeListener = this
                }
            }

            override fun onTextChanged(
                text: CharSequence,
                cursorPosition: Int,
                before: Int,
                count: Int
            ) {
                super.onTextChanged(text, cursorPosition, before, count)
                if (inputmvi != null) {
                    btnmvi.isEnabled = inputmvi.length() == 9
                }
            }
        }
        editText.addTextChangedListener(listener)
        editText.onFocusChangeListener = listener
    }

    private fun unMask(s: String): String {
        return s.replace(".", "").replace("-", "")
    }

    private fun Any.toast(duration: Int = Toast.LENGTH_LONG): Toast {
        return Toast.makeText(context, this.toString(), duration).apply { show() }
    }

    private fun navigation(cepResponse: CepResponse) {
        val action = CepFragmentDirections.actionCepFragmentToCepSecondFragment(cepResponse)
        findNavController().navigate(action)
    }

}