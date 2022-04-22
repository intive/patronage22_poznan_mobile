package com.intive.patronage22.intivi

import android.util.Patterns.EMAIL_ADDRESS

object LoginValidation {

    fun checkEmailError(email: String): Int?{
        val localPart = email.split("@").first()
        val domainPart = email.split("@").last()
        //val printableCharacters = Regex("""\\!#%\$&'\*\+-/=\?\^_`\{\|}~""")
        return when{
            email.isEmpty() -> R.string.emailIsEmpty
            email.length > 320 -> R.string.emailTooLongError
            domainPart.length > 255 -> R.string.emailDomainPartTooLongError
            localPart.length > 64 -> R.string.emailLocalPartTooLongError
            !localPart.matches(Regex("^[0-9A-Za-z].*")) -> R.string.emailStartWithError
            !localPart.matches(Regex(".*[0-9A-Za-z]$")) -> R.string.emailEndWithError
            email.contains(Regex("""\.\.""")) -> R.string.emailConsecutiveDotsError
            !EMAIL_ADDRESS.matcher(email).matches() -> R.string.emailInvalidError
            else -> null
        }
    }

    fun checkPasswordError(password: String): Int? {
        return when{
            password.length < 8 -> R.string.passwordTooShort
            password.length > 128 -> R.string.passwordTooLong
            !password.contains(Regex("(?=.*[0-9])")) -> R.string.passwordNoDigit
            !password.contains(Regex("(?=.*[a-z])")) -> R.string.passwordNoLowercase
            !password.contains(Regex("(?=.*[A-Z])")) -> R.string.passwordNoUppercase
            !password.contains(Regex("""(?=.*[@#$%^&+=!?*()\[\]{}\\.,<>/;:\-_|`~'"])""")) -> R.string.passwordNoSpecialCharacter
            else -> null
        }
    }

    fun checkSecondPasswordError(password: String, repeatPassword: String): Int? {
        return if(password != repeatPassword) R.string.passwordsDoNotMatch
        else null
    }
}