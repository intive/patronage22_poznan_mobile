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
import androidx.fragment.app.activityViewModels
import com.intive.patronage22.intivi.MainActivity
import com.intive.patronage22.intivi.OnTextChangeListener
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.ViewModels.LoginViewModel
import com.intive.patronage22.intivi.databinding.FragmentSignUpBinding
import java.util.regex.Pattern

class SignUpFragment : Fragment() {

    private lateinit var bind: FragmentSignUpBinding
    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentSignUpBinding.inflate(layoutInflater)

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

    override fun onPause() {
        super.onPause()
        bind.repeatPasswordTextInputLayout?.editText?.text?.clear()
    }

    private fun isEmailOK(): Boolean {
        val editText = requireView().findViewById<EditText>(R.id.loginEditText)
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
        val repeatPassword = requireView().findViewById<EditText>(R.id.repeatPasswordEditText)
        if (password.text.toString() != repeatPassword.text.toString()) {
            repeatPassword.error = getString(R.string.repeatPassValidMessage)
            return false
        } else {
            return true
        }
    }

    private fun isValid() = isEmailOK() and isPasswordOK() and isRepeatPasswordOK()
}