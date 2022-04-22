package com.intive.patronage22.intivi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.textfield.TextInputLayout
import com.intive.patronage22.intivi.OnTextChangeListener
import com.intive.patronage22.intivi.databinding.FragmentSignUpBinding
import com.intive.patronage22.intivi.viewmodel.LoginViewModel

class SignUpFragment : Fragment() {

    private lateinit var emailTextInput: TextInputLayout
    private lateinit var passwordTextInput: TextInputLayout
    private lateinit var repeatPasswordTextInput: TextInputLayout
    private lateinit var bind: FragmentSignUpBinding
    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentSignUpBinding.inflate(layoutInflater)

        emailTextInput = bind.emailTextInputLayout
        passwordTextInput = bind.passwordTextInputLayout
        repeatPasswordTextInput = bind.repeatPasswordTextInputLayout


        bind.signUpButton.setOnClickListener {
            loginViewModel.onRegisterButtonClicked()
        }

        loginViewModel.emailErrorMessage.observe(viewLifecycleOwner) {
            if (it != null) {
                emailTextInput.error = resources.getString(it)
            } else emailTextInput.error = null
        }

        loginViewModel.passwordErrorMessage.observe(viewLifecycleOwner) {
            if (it != null) {
                passwordTextInput.error = resources.getString(it)
            } else passwordTextInput.error = null
        }

        loginViewModel.repeatPasswordErrorMessage.observe(viewLifecycleOwner) {
            if (it != null) {
                repeatPasswordTextInput.error = resources.getString(it)
            } else repeatPasswordTextInput.error = null
        }

        emailTextInput.editText?.addTextChangedListener(object : OnTextChangeListener {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (loginViewModel.emailHolder.value != p0.toString()) {
                    loginViewModel.updateEmail(p0.toString())
                }
            }
        })

        passwordTextInput.editText?.addTextChangedListener(object : OnTextChangeListener {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (loginViewModel.passwordHolder.value != p0.toString()) {
                    loginViewModel.updatePassword(p0.toString())
                }
            }
        })

        repeatPasswordTextInput.editText?.addTextChangedListener(object : OnTextChangeListener {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (loginViewModel.secondPasswordHolder.value != p0.toString()) {
                    loginViewModel.updateSecondPassword(p0.toString())
                }
            }
        })

        loginViewModel.emailHolder.observe(viewLifecycleOwner) { emailHolder ->
            if (emailTextInput.editText?.text.toString() != emailHolder) {
                emailTextInput.editText?.setText(emailHolder)
            }
        }

        loginViewModel.passwordHolder.observe(viewLifecycleOwner) { passwordHolder ->
            if (passwordTextInput.editText?.text.toString() != passwordHolder) {
                passwordTextInput.editText?.setText(passwordHolder)
            }
        }

        return bind.root
    }
}