package com.intive.patronage22.intivi.Fragments

import android.widget.EditText
import androidx.fragment.app.Fragment
import com.intive.patronage22.intivi.R
import java.util.regex.Pattern

class Validation : Fragment() {

    fun isLoginFormValid() =
        isEmailOK(R.id.editTextLoginEmail) and isPasswordOK(R.id.editTextLoginPassword)

    fun isRegisterFormValid() =
        isEmailOK(R.id.editTextRegisterEmail) and isPasswordOK(R.id.editTextRegisterPassword) and isRepeatPasswordOK()

    private fun isEmailOK(emailTextId: Int): Boolean {
        val editText = requireView().findViewById<EditText>(emailTextId)
        val email = editText.text.toString()
        val localPartLength = email.split("@").first().length
        val domainPartLength = email.split("@").last().length
        if (!isEmailValid(email) || localPartLength > 64 || domainPartLength > 255) {
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

    private fun isPasswordOK(passwordTextId: Int): Boolean {
        val editText = requireView().findViewById<EditText>(passwordTextId)
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
        val password = requireView().findViewById<EditText>(R.id.editTextRegisterPassword)
        val repeatPassword = requireView().findViewById<EditText>(R.id.editTextRegisterRepeatPassword)
        if (password.text.toString() != repeatPassword.text.toString()) {
            repeatPassword.error = getString(R.string.repeatPassValidMessage)
            return false
        } else {
            return true
        }
    }
}