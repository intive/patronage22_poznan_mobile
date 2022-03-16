package com.intive.patronage22.intivi.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.intive.patronage22.intivi.*
import com.intive.patronage22.intivi.databinding.FragmentSignUpBinding

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
            }
        }
        return bind.root
    }

    private fun isValid() = isRegisterFormValid(
        requireView().findViewById(R.id.editTextRegisterEmail),
        requireView().findViewById(R.id.editTextRegisterPassword),
        requireView().findViewById(R.id.editTextRegisterRepeatPassword)
    )
}