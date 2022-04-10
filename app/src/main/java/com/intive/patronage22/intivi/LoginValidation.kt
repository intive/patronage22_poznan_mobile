package com.intive.patronage22.intivi

import android.util.Patterns.EMAIL_ADDRESS

object LoginValidation {

//    fun isEmailValid(email: String): Boolean {
//        return try {
//            val localPart = email.split("@").first()
//            val domainPartLength = email.split("@").last().length
//            val localPartLength = localPart.length
//            val chars = arrayOf('.', '-', '_')
//            val starts: Boolean = chars.contains(localPart.first())
//            val ends: Boolean = chars.contains(localPart.last())
//            val regex = "\\.\\.".toRegex()
//            val dots: Boolean = regex.containsMatchIn(localPart)
//            !(EMAIL_ADDRESS.matcher(email).matches() || localPartLength > 64 || domainPartLength > 255 || starts || ends || dots)
//        } catch (e: Exception) {
//            false
//        }
//    }
//
//    fun isEmailValid2(email: String): Boolean {
//        return EMAIL_ADDRESS.matcher(email).matches()
//    }

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