package com.intive.patronage22.intivi.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.intive.patronage22.intivi.*
import com.intive.patronage22.intivi.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private var registerEmail: EditText? = null
    private var registerPassword: EditText? = null
    private var registerRepeatPassword: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bind = FragmentSignUpBinding.inflate(layoutInflater)

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
            }
        }
        return bind.root
    }
}