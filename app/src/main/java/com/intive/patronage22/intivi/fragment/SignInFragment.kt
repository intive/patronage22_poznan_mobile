package com.intive.patronage22.intivi.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.intive.patronage22.intivi.*
import com.intive.patronage22.intivi.api.ApiClient
import com.intive.patronage22.intivi.api.SessionManager
import com.intive.patronage22.intivi.viewmodel.LoginViewModel
import com.intive.patronage22.intivi.databinding.FragmentSignInBinding
import com.intive.patronage22.intivi.model.SignInResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

        bind.forgotPassText.setOnClickListener {
            bind.emailHasBeenSentText.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                bind.emailHasBeenSentText.visibility = View.INVISIBLE
            }, 5000)
        }

        loginEmail = bind.emailTextInputLayout.editText
        loginPassword = bind.passwordTextInputLayout.editText
        val emailValidMess: String = getString(R.string.emailValidMessage)
        val passwordValidMess: String = getString(R.string.passwordValidMessage)

        fun isValid() = isLoginFormValid(loginEmail!!, loginPassword!!,
            emailValidMess, passwordValidMess)

        bind.signInButton.setOnClickListener {
            if (isValid()) {
                val intent = Intent(this.requireContext(), MainActivity::class.java)
                loginViewModel.sessionManager = SessionManager(this.requireContext())

                ApiClient().getService()?.signIn(loginEmail?.text.toString(),loginPassword?.text.toString())
                    ?.enqueue(object : Callback<SignInResponse> {

                        override fun onResponse(call: Call<SignInResponse>, response: Response<SignInResponse>) {
                            val loginResponse = response.body()
                            if(response.isSuccessful) {
                                if (loginResponse?.token != null) {
                                    loginViewModel.sessionManager.saveAuthToken(loginResponse.token)
                                    startActivity(intent)
                                    activity?.onBackPressed()
                                }
                            }
                            else {
                                Toast.makeText(requireContext(), "Incorrect e-mail or password", Toast.LENGTH_SHORT).show()
                            }
                        }
                        override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                        }
                    })
            }
        }

        loginEmail?.addTextChangedListener(object: OnTextChangeListener {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(loginViewModel.emailHolder.value != p0.toString()) {
                    loginViewModel.updateEmail(p0.toString())
                }
            }
        })

        loginPassword?.addTextChangedListener(object : OnTextChangeListener {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (loginViewModel.passwordHolder.value != p0.toString()) {
                    loginViewModel.updatePassword(p0.toString())
                }
            }
        })

        loginViewModel.emailHolder.observe(viewLifecycleOwner) { emailHolder ->
            if(loginEmail?.text.toString() != emailHolder) {
                loginEmail?.setText(emailHolder)
            }
        }

        loginViewModel.passwordHolder.observe(viewLifecycleOwner) { passwordHolder ->
            if(loginPassword?.text.toString() != passwordHolder) {
                loginPassword?.setText(passwordHolder)
            }
        }

        return bind.root
    }
}