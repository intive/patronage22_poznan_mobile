package com.intive.patronage22.intivi.Fragments

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.intive.patronage22.intivi.*
import com.intive.patronage22.intivi.databinding.FragmentSignUpBinding
import java.util.*

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bind = FragmentSignUpBinding.inflate(layoutInflater)

        bind.editTextRegisterEmail.filters = arrayOf<InputFilter>(object : InputFilter.AllCaps() {
            override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int) =
                source.toString().lowercase(Locale.getDefault())
        })

        bind.signUpButton.setOnClickListener {
            if (isRegisterFormValid(bind.editTextRegisterEmail, bind.editTextRegisterPassword, bind.editTextRegisterRepeatPassword)) {
                val intent = Intent(this.requireContext(), MainActivity::class.java)
                startActivity(intent)
            }
        }
        return bind.root
    }
}