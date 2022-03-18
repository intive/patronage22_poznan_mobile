package com.intive.patronage22.intivi.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.intive.patronage22.intivi.*
import com.intive.patronage22.intivi.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bind = FragmentSignUpBinding.inflate(layoutInflater)

        upperToLowerCase(bind.editTextRegisterEmail)

        fun isValid() = isRegisterFormValid(
            bind.editTextRegisterEmail,
            bind.editTextRegisterPassword,
            bind.editTextRegisterRepeatPassword,
            getString(R.string.emailValidMessage),
            getString(R.string.passwordValidMessage),
            getString(R.string.repeatPassValidMessage)
        )

        bind.signUpButton.setOnClickListener {
            if (isValid()) {
                val intent = Intent(this.requireContext(), MainActivity::class.java)
                startActivity(intent)
            }
        }
        return bind.root
    }
}