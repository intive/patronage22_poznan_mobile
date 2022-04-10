package com.intive.patronage22.intivi.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.intive.patronage22.intivi.*
import androidx.fragment.app.activityViewModels
import com.google.android.material.textfield.TextInputLayout
import com.intive.patronage22.intivi.MainActivity
import com.intive.patronage22.intivi.OnTextChangeListener
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.ViewModels.LoginViewModel
import com.intive.patronage22.intivi.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

//    private var registerEmail: EditText? = null
//    private var registerPassword: EditText? = null
//    private var registerRepeatPassword: EditText? = null
    private lateinit var emailTextInput: TextInputLayout
    private lateinit var passwordTextInput: TextInputLayout
    private lateinit var repeatPasswordTextInput: TextInputLayout
    private lateinit var bind: FragmentSignUpBinding
    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentSignUpBinding.inflate(layoutInflater)

//        registerEmail = bind.emailTextInputLayout.editText
//        registerPassword = bind.passwordTextInputLayout.editText
//        registerRepeatPassword = bind.repeatPasswordTextInputLayout.editText
        emailTextInput = bind.emailTextInputLayout
        passwordTextInput = bind.passwordTextInputLayout
        repeatPasswordTextInput = bind.repeatPasswordTextInputLayout

        bind.signUpButton.setOnClickListener {
                loginViewModel.onRegisterButtonClicked()
        }

        loginViewModel.emailErrorMessage.observe(viewLifecycleOwner){
            if(it!=null) {
                emailTextInput.error = resources.getString(it)
            } else emailTextInput.error = null
        }

        loginViewModel.passwordErrorMessage.observe(viewLifecycleOwner){
            if(it!=null) {
                passwordTextInput.error = resources.getString(it)
            } else passwordTextInput.error = null
        }

        loginViewModel.repeatPasswordErrorMessage.observe(viewLifecycleOwner){
            if(it!=null) {
                repeatPasswordTextInput.error = resources.getString(it)
            } else repeatPasswordTextInput.error = null
        }

        emailTextInput.editText?.addTextChangedListener(object: OnTextChangeListener {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(loginViewModel.emailHolder.value != p0.toString()) {
                    loginViewModel.updateEmail(p0.toString())
                }
            }
        })

        passwordTextInput.editText?.addTextChangedListener(object: OnTextChangeListener {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(loginViewModel.passwordHolder.value != p0.toString()) {
                    loginViewModel.updatePassword(p0.toString())
                }
            }
        })

        repeatPasswordTextInput.editText?.addTextChangedListener(object: OnTextChangeListener {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(loginViewModel.secondPasswordHolder.value != p0.toString()) {
                    loginViewModel.updateSecondPassword(p0.toString())
                }
            }
        })

        loginViewModel.emailHolder.observe(viewLifecycleOwner) { emailHolder ->
            if(emailTextInput.editText?.text.toString() != emailHolder) {
                emailTextInput.editText?.setText(emailHolder)
            }
        }

        loginViewModel.passwordHolder.observe(viewLifecycleOwner) { passwordHolder ->
            if(passwordTextInput.editText?.text.toString() != passwordHolder) {
                passwordTextInput.editText?.setText(passwordHolder)
            }
        }

        return bind.root
    }
}