package com.intive.patronage22.intivi.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.intive.patronage22.intivi.*
import com.intive.patronage22.intivi.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bind = FragmentSignInBinding.inflate(layoutInflater)

        bind.signInButton.setOnClickListener {
            if (isValid()) {
                val intent = Intent(this.requireContext(), MainActivity::class.java)
                startActivity(intent)
            }
        }
        return bind.root
    }

    private fun isValid() = isLoginFormValid(
        requireView().findViewById(R.id.editTextLoginEmail),
        requireView().findViewById(R.id.editTextLoginPassword)
    )
}