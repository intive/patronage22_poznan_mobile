package com.intive.patronage22.intivi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.intive.patronage22.intivi.*
import androidx.fragment.app.activityViewModels
import com.intive.patronage22.intivi.interfaces.OnTextChangeListener
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.viewmodels.LoginViewModel
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

        registerEmail = bind.emailTextInputLayout.editText
        registerPassword = bind.passwordTextInputLayout.editText
        registerRepeatPassword = bind.repeatPasswordTextInputLayout.editText
        val emailValidMess: String = getString(R.string.emailValidMessage)
        val passwordValidMess: String = getString(R.string.passwordValidMessage)
        val repeatPassValidMess: String = getString(R.string.repeatPassValidMessage)

        fun isValid() = isRegisterFormValid(registerEmail!!, registerPassword!!, registerRepeatPassword!!,
            emailValidMess, passwordValidMess, repeatPassValidMess)

        bind.signUpButton.setOnClickListener {
            if (isValid()) {
                loginViewModel.registerUser()
                //activity?.onBackPressed()
            }
        }

        registerEmail?.addTextChangedListener(object: OnTextChangeListener {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(loginViewModel.emailHolder.value != p0.toString()) {
                    loginViewModel.updateEmail(p0.toString())
                }
            }
        })

        registerPassword?.addTextChangedListener(object: OnTextChangeListener {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(loginViewModel.passwordHolder.value != p0.toString()) {
                    loginViewModel.updatePassword(p0.toString())
                }
            }
        })

        loginViewModel.emailHolder.observe(viewLifecycleOwner) { emailHolder ->
            if(registerEmail?.text.toString() != emailHolder) {
                registerEmail?.setText(emailHolder)
            }
        }

        loginViewModel.passwordHolder.observe(viewLifecycleOwner) { passwordHolder ->
            if(registerPassword?.text.toString() != passwordHolder) {
                registerPassword?.setText(passwordHolder)
            }
        }

        return bind.root
    }
}