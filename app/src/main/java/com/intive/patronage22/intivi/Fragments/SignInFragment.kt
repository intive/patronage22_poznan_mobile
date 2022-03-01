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

import com.intive.patronage22.intivi.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private var isLoginFormValid: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bind = FragmentSignInBinding.inflate(layoutInflater)

        bind.signInButton.setOnClickListener{
            isLoginFormValid = isLoginOK(bind.root.rootView) and isPasswordOK(bind.root.rootView)
            if (isLoginFormValid) {
                val intent = Intent(this.requireContext(), MainActivity::class.java)
                startActivity(intent)
            }
        }
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        isLoginOK(view)
        isPasswordOK(view)
        return
    }

    private fun isLoginOK(view: View): Boolean {
        val editText = view.findViewById<EditText>(R.id.loginEditText)
        if (editText.text.isEmpty()) {
            editText.error = ""
            return false
        } else {
            return true
        }
    }

    private fun isPasswordOK(view: View): Boolean {
        val editText = view.findViewById<EditText>(R.id.passwordEditText)
        if (editText.text.length < 5) {
            editText.error = ""
            return false
        } else {
            return true
        }
    }
}