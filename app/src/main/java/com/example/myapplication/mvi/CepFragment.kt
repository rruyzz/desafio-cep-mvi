package com.example.myapplication.mvi

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.mvi.UiStateMachine
import com.example.myapplication.R
import com.redmadrobot.inputmask.MaskedTextChangedListener
import kotlinx.android.synthetic.main.fragment_cep.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.progressBar
import kotlinx.android.synthetic.main.fragment_main.textInputLayout3
import org.koin.androidx.viewmodel.ext.android.viewModel


class CepFragment : CepMVIFragment() {

    override val uiStateMachine: UiStateMachine<CepStates> get() = viewModel
    private val viewModel : CepViewModel by viewModel()
    lateinit var cep: String
    private val errorCep = "Error cep"
    private val successCep = "Success cep"
    private val sessionExpired = "Session expired"


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
        viewModel.mutate(
            CepActions.CepRequestAction(cep)
        )
    }

    override fun render(state: CepStates) {
        when(state.stateType){
            is StateType.SuccessCep -> renderSucessCep(state)
            is StateType.ErrorCep -> renderErroCep(state)
            is StateType.SessionExpired -> renderSessionExpired(state)
        }
    }
    private fun setUp(){

    }

    private fun renderSucessCep(state: CepStates){
        successCep.toast()
    }

    private fun renderErroCep(state: CepStates){
        errorCep.toast()
    }

    private fun renderSessionExpired(state: CepStates){
        sessionExpired.toast()
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
                btn.isEnabled = input.length() == 9
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

    private fun hideLoanding(){
        progressBarCep.visibility = View.INVISIBLE
    }

    private fun showLoanding(){
        progressBarCep.visibility = View.VISIBLE
    }

    private fun hideViews(){
        textInputLayoutMvi.visibility = View.INVISIBLE
        btnmvi.visibility = View.INVISIBLE
        inputmvi.visibility = View.INVISIBLE
    }

    private fun showViews(){
        textInputLayoutMvi.visibility = View.VISIBLE
        btnmvi.visibility = View.VISIBLE
        inputmvi.visibility = View.VISIBLE
    }

}