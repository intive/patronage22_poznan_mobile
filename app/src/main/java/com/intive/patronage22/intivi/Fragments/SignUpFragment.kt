package com.intive.patronage22.intivi.Fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.intive.patronage22.intivi.*
import androidx.fragment.app.activityViewModels
import com.intive.patronage22.intivi.MainActivity
import com.intive.patronage22.intivi.OnTextChangeListener
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.ViewModels.LoginViewModel
import com.intive.patronage22.intivi.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private var registerEmail: EditText? = null
    private var registerPassword: EditText? = null
    private var registerRepeatPassword: EditText? = null
    private lateinit var bind: FragmentSignUpBinding
    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentSignUpBinding.inflate(layoutInflater)

        registerEmail = bind.editTextRegisterEmail
        registerPassword = bind.editTextRegisterPassword
        registerRepeatPassword = bind.editTextRegisterRepeatPassword
        val emailValidMess: String = getString(R.string.emailValidMessage)
        val passwordValidMess: String = getString(R.string.passwordValidMessage)
        val repeatPassValidMess: String = getString(R.string.repeatPassValidMessage)

        upperToLowerCase(registerEmail!!)

        fun isValid() = isRegisterFormValid(registerEmail!!, registerPassword!!, registerRepeatPassword!!,
            emailValidMess, passwordValidMess, repeatPassValidMess)

        bind.signUpButton.setOnClickListener {
            if (isValid()) {
                val intent = Intent(this.requireContext(), MainActivity::class.java)
                startActivity(intent)
                activity?.onBackPressed()
            }
        }

        bind.emailTextInputLayout?.editText?.addTextChangedListener(object: OnTextChangeListener {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(loginViewModel.emailHolder.value != p0.toString()) {
                    loginViewModel.updateEmail(p0.toString())
                }
            }
        })

        bind.passwordTextInputLayout?.editText?.addTextChangedListener(object: OnTextChangeListener {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(loginViewModel.passwordHolder.value != p0.toString()) {
                    loginViewModel.updatePassword(p0.toString())
                }
            }
        })

        loginViewModel.emailHolder.observe(viewLifecycleOwner) { emailHolder ->
            if(bind.emailTextInputLayout?.editText?.text.toString() != emailHolder) {
                bind.emailTextInputLayout?.editText?.setText(emailHolder)
            }
        }

        loginViewModel.passwordHolder.observe(viewLifecycleOwner) { passwordHolder ->
            if(bind.passwordTextInputLayout?.editText?.text.toString() != passwordHolder) {
                bind.passwordTextInputLayout?.editText?.setText(passwordHolder)
            }
        }

        return bind.root
    }
}