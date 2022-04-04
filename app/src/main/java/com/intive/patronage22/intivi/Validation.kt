package com.intive.patronage22.intivi

import android.util.Patterns.EMAIL_ADDRESS
import android.widget.EditText
import java.util.*

fun isLoginFormValid(email: EditText, password: EditText, emailMess: String, passMess: String): Boolean {
    setEmailValidMessage(email, emailMess)
    setPasswordValidMessage(password, passMess)
    return isEmailOK(email) and isPasswordOK(password)
}

fun isRegisterFormValid(email: EditText, password: EditText, repeatPassword: EditText, emailMess: String, passMess: String, repeatPassMess: String): Boolean {
    setEmailValidMessage(email, emailMess)
    setPasswordValidMessage(password, passMess)
    setRepeatPasswordValidMessage(password, repeatPassword, repeatPassMess)
    return isEmailOK(email) and isPasswordOK(password) and isRepeatPasswordOK(password, repeatPassword)
}

private fun setEmailValidMessage(email: EditText, message: String) {
    if (!isEmailOK(email)) {
        email.error = message
    }
}

private fun setPasswordValidMessage(password: EditText, message: String) {
    if (!isPasswordOK(password)) {
        password.error = message
    }
}

private fun setRepeatPasswordValidMessage(password: EditText, repeatPass: EditText, message: String) {
    if (!isRepeatPasswordOK(password, repeatPass)) {
        repeatPass.error = message
    }
}

private fun isEmailOK(editText: EditText): Boolean {
    val email = editText.text.toString()
    try {
        val localPart = email.split("@").first()
        val domainPartLength = email.split("@").last().length
        val localPartLength = localPart.length
        val chars = arrayOf('.', '-', '_')
        val starts: Boolean = chars.contains(localPart.first())
        val ends: Boolean = chars.contains(localPart.last())
        val regex = "\\.\\.".toRegex()
        val dots: Boolean = regex.containsMatchIn(localPart)
        return !(!isEmailValid(email) || localPartLength > 64 || domainPartLength > 255 || starts || ends || dots)
    } catch (e: Exception) {
        return false
    }
}

private fun isEmailValid(email: String): Boolean {
    return EMAIL_ADDRESS.matcher(email).matches()
}

private fun isPasswordOK(password: EditText): Boolean {
    return !(password.text.length !in (5..40) || (password.text.toString()
        .filterNot { it.isWhitespace() }.length != password.text.length))
}

private fun isRepeatPasswordOK(password: EditText, repeatPassword: EditText): Boolean {
    return password.text.toString() == repeatPassword.text.toString()
}