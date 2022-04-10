package com.intive.patronage22.intivi.Fragments

import android.content.Context
import android.os.Bundle
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
import com.google.android.material.textfield.TextInputLayout
import com.intive.patronage22.intivi.MainActivity
import com.intive.patronage22.intivi.OnTextChangeListener
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.ViewModels.LoginViewModel
import com.intive.patronage22.intivi.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

//    private var loginEmail: EditText? = null
//    private var loginPassword: EditText? = null
    private lateinit var emailTextInput: TextInputLayout
    private lateinit var passwordTextInput: TextInputLayout
    private val loginViewModel: LoginViewModel by activityViewModels()
    private lateinit var bind: FragmentSignInBinding
  
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentSignInBinding.inflate(layoutInflater)

        bind.forgotPassText.setOnClickListener {
            bind.emailHasBeenSentText.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                bind.emailHasBeenSentText.visibility = View.INVISIBLE
            }, 5000)
        }

//        loginEmail = bind.emailTextInputLayout.editText
//        loginPassword = bind.passwordTextInputLayout.editText
        emailTextInput = bind.emailTextInputLayout
        passwordTextInput = bind.passwordTextInputLayout

        bind.signInButton.setOnClickListener {
                loginViewModel.onSignInButtonClicked()
        }

        loginViewModel.emailErrorMessage.observe(viewLifecycleOwner){
            if(it!=null) {
                //loginEmail?.error = resources.getString(it)
                emailTextInput.error = resources.getString(it)
            } else emailTextInput.error = null
        }

        loginViewModel.passwordErrorMessage.observe(viewLifecycleOwner){
            if(it!=null) {
                passwordTextInput.error = resources.getString(it)
            } else passwordTextInput.error = null
        }

        //loginEmail?.addTextChangedListener(object: OnTextChangeListener {
        emailTextInput.editText?.addTextChangedListener(object: OnTextChangeListener {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(loginViewModel.emailHolder.value != p0.toString()) {
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

        loginViewModel.emailHolder.observe(viewLifecycleOwner) { emailHolder ->
            if(emailTextInput.editText?.text.toString() != emailHolder) {
                emailTextInput.editText?.setText(emailHolder)
            }
        }

        loginViewModel.passwordHolder.observe(viewLifecycleOwner) { passwordHolder ->
            if(passwordTextInput.editText?.text.toString() != passwordHolder) {
                passwordTextInput.editText?.setText(passwordHolder)
            }
        }

        return bind.root
    }
}