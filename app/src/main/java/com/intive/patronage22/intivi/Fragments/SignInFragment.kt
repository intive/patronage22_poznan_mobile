package com.intive.patronage22.intivi.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.intive.patronage22.intivi.*
import com.intive.patronage22.intivi.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private var loginEmail: EditText? = null
    private var loginPassword: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bind = FragmentSignInBinding.inflate(layoutInflater)

        loginEmail = bind.editTextLoginEmail
        loginPassword = bind.editTextLoginPassword
        val emailValidMess: String = getString(R.string.emailValidMessage)
        val passwordValidMess: String = getString(R.string.passwordValidMessage)

        upperToLowerCase(loginEmail!!)

        fun isValid() = isLoginFormValid(loginEmail!!, loginPassword!!,
            emailValidMess, passwordValidMess)

        bind.signInButton.setOnClickListener {
            if (isValid()) {
                val intent = Intent(this.requireContext(), MainActivity::class.java)
                startActivity(intent)
            }
        }
        return bind.root
    }
}