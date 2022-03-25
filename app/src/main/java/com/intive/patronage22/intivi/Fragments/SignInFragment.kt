package com.intive.patronage22.intivi.Fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.intive.patronage22.intivi.*
import androidx.fragment.app.activityViewModels
import com.intive.patronage22.intivi.MainActivity
import com.intive.patronage22.intivi.OnTextChangeListener
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.ViewModels.LoginViewModel
import com.intive.patronage22.intivi.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private var loginEmail: EditText? = null
    private var loginPassword: EditText? = null
    private val loginViewModel: LoginViewModel by activityViewModels()
    private lateinit var bind: FragmentSignInBinding
  
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentSignInBinding.inflate(layoutInflater)

        bind.forgotpassText.setOnClickListener {
            bind.emailHasBeenSentText.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                bind.emailHasBeenSentText.visibility = View.INVISIBLE
            }, 5000)
        }

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
                activity?.onBackPressed()
            }
        }

        bind.loginEditText.addTextChangedListener(object: OnTextChangeListener {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(loginViewModel.emailHolder.value != p0.toString()) {
                    loginViewModel.updateEmail(p0.toString())
                }
            }
        })

        bind.passwordEditText.addTextChangedListener(object : OnTextChangeListener {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (loginViewModel.passwordHolder.value != p0.toString()) {
                    loginViewModel.updatePassword(p0.toString())
                }
            }
        })

        loginViewModel.emailHolder.observe(viewLifecycleOwner) { emailHolder ->
            if(bind.loginEditText.text.toString() != emailHolder) {
                bind.loginEditText.setText(emailHolder)
            }
        }

        loginViewModel.passwordHolder.observe(viewLifecycleOwner) { passwordHolder ->
            if(bind.passwordEditText.text.toString() != passwordHolder) {
                bind.passwordEditText.setText(passwordHolder)
            }
        }

        return bind.root
    }
}