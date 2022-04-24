package com.intive.patronage22.intivi.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.textfield.TextInputLayout
import com.intive.patronage22.intivi.OnTextChangeListener
import com.intive.patronage22.intivi.databinding.FragmentSignInBinding
import com.intive.patronage22.intivi.viewmodel.LoginViewModel

class SignInFragment : Fragment() {

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

        emailTextInput = bind.emailTextInputLayout
        passwordTextInput = bind.passwordTextInputLayout

        bind.signInButton.setOnClickListener {
            if(loginViewModel.loadingStatus.value == false) {
                loginViewModel.onSignInButtonClicked()
            }
        }

        emailTextInput.editText?.addTextChangedListener(object : OnTextChangeListener {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (loginViewModel.emailHolder.value != p0.toString()) {
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
            if (emailTextInput.editText?.text.toString() != emailHolder) {
                emailTextInput.editText?.setText(emailHolder)
            }
        }

        loginViewModel.passwordHolder.observe(viewLifecycleOwner) { passwordHolder ->
            if (passwordTextInput.editText?.text.toString() != passwordHolder) {
                passwordTextInput.editText?.setText(passwordHolder)
            }
        }

        loginViewModel.emailErrorMessage.observe(viewLifecycleOwner) {
            if (it != null) {
                emailTextInput.error = resources.getString(it)
            } else emailTextInput.error = null
        }

        loginViewModel.passwordErrorMessage.observe(viewLifecycleOwner) {
            if (it != null) {
                passwordTextInput.error = resources.getString(it)
            } else passwordTextInput.error = null
        }

        return bind.root
    }
}