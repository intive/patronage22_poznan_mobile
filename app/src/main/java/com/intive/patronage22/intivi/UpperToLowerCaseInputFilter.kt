package com.intive.patronage22.intivi

import android.text.InputFilter
import android.text.Spanned
import android.widget.EditText
import java.util.*

fun upperToLowerCase(editText: EditText) {
    editText.filters = arrayOf<InputFilter>(object : InputFilter {
        override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int) =
            source.toString().lowercase(Locale.getDefault())
    })
}