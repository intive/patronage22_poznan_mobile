package com.intive.patronage22.intivi

import android.widget.EditText
import java.util.regex.Pattern

fun isLoginFormValid(email: EditText, password: EditText): Boolean {
    return isEmailOK(email) and isPasswordOK(password)
}

fun isRegisterFormValid(email: EditText, password: EditText, repeatPassword: EditText): Boolean {
    return isEmailOK(email) and isPasswordOK(password) and isRepeatPasswordOK(password, repeatPassword)
}

private fun isEmailOK(editText: EditText): Boolean {
    val email = editText.text.toString()
    val localPartLength = email.split("@").first().length
    val domainPartLength = email.split("@").last().length
    if (!isEmailValid(email) || localPartLength > 64 || domainPartLength > 255) {
        editText.error = "Please enter a correct e-mail address"
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

private fun isPasswordOK(editText: EditText): Boolean {
    if (editText.text.length !in (5..40) || (editText.text.toString()
            .filterNot { it.isWhitespace() }.length != editText.text.length)
    ) {
        editText.error = "Password must be between 5 and 40 characters long (no spaces)"
        return false
    } else {
        return true
    }
}

private fun isRepeatPasswordOK(password: EditText, repeatPassword: EditText): Boolean {
    if (password.text.toString() != repeatPassword.text.toString()) {
        repeatPassword.error = "Passwords do not match"
        return false
    } else {
        return true
    }
}