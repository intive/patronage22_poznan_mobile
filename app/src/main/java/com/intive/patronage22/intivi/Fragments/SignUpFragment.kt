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

        bind.SignUpButton.setOnClickListener {
            if (isEmailOK() and isPasswordOK() and isRepeatPasswordOK()) {
                val intent = Intent(this.requireContext(), MainActivity::class.java)
                startActivity(intent)
            }
        }
        return bind.root
    }

    private fun isEmailOK(): Boolean {
        val editText = requireView().findViewById<EditText>(R.id.loginEditText)
        if (!isEmailValid(editText.text.toString())) {
            editText.error = "This is not an e-mail address"
            return false
        } else {
            return true
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "[a-zA-Z0-9+._%-]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
        ).matcher(email).matches()
    }

    private fun isPasswordOK(): Boolean {
        val editText = requireView().findViewById<EditText>(R.id.passwordEditText)
        if (editText.text.length < 5) {
            editText.error = "Password must be at least 5 characters long"
            return false
        } else {
            return true
        }
    }

    private fun isRepeatPasswordOK(): Boolean {
        val password = requireView().findViewById<EditText>(R.id.passwordEditText)
        val repeatPassword = requireView().findViewById<EditText>(R.id.repeatPassword)
        if (password.text.toString() != repeatPassword.text.toString()) {
            repeatPassword.error = "Passwords do not match"
            return false
        } else {
            return true
        }
    }
}