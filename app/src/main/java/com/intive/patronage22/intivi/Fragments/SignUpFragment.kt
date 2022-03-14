package com.intive.patronage22.intivi.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.intive.patronage22.intivi.MainActivity
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.databinding.FragmentSignUpBinding
import java.util.regex.Pattern

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bind = FragmentSignUpBinding.inflate(layoutInflater)

        bind.signUpButton.setOnClickListener {
            if (isValid()) {
                val intent = Intent(this.requireContext(), MainActivity::class.java)
                startActivity(intent)

                bind.loginEditText.text.clear()
                bind.passwordEditText.text.clear()
                bind.repeatPassword.text.clear()
            }
        }
        return bind.root
    }

    private fun isEmailOK(): Boolean {
        val editText = requireView().findViewById<EditText>(R.id.loginEditText)
        if (!isEmailValid(editText.text.toString())) {
            editText.error = getString(R.string.emailValidMessage)
            return false
        } else {
            return true
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            """(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])"""
        ).matcher(email).matches()
    }

    private fun isPasswordOK(): Boolean {
        val editText = requireView().findViewById<EditText>(R.id.passwordEditText)
        if (editText.text.length !in (5..40) || (editText.text.toString()
                .filterNot { it.isWhitespace() }.length != editText.text.length)
        ) {
            editText.error = getString(R.string.passwordValidMessage)
            return false
        } else {
            return true
        }
    }

    private fun isRepeatPasswordOK(): Boolean {
        val password = requireView().findViewById<EditText>(R.id.passwordEditText)
        val repeatPassword = requireView().findViewById<EditText>(R.id.repeatPassword)
        if (password.text.toString() != repeatPassword.text.toString()) {
            repeatPassword.error = getString(R.string.repeatPassValidMessage)
            return false
        } else {
            return true
        }
    }

    private fun isValid() = isEmailOK() and isPasswordOK() and isRepeatPasswordOK()
}