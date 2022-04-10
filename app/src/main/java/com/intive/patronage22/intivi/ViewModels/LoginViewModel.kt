package com.intive.patronage22.intivi.ViewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.intive.patronage22.intivi.LoginValidation.checkEmailError
import com.intive.patronage22.intivi.LoginValidation.checkPasswordError
import com.intive.patronage22.intivi.LoginValidation.checkSecondPasswordError
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.database.LogInEvent
import com.intive.patronage22.intivi.database.User
import com.intive.patronage22.intivi.database.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel(){

    lateinit var userRepo: UserRepository

    private val _logInEvent = MutableLiveData(LogInEvent(null))
    val logInEvent: LiveData<LogInEvent> = _logInEvent

    private val _emailHolder = MutableLiveData("")
    val emailHolder: LiveData<String> = _emailHolder
    private val _passwordHolder = MutableLiveData("")
    val passwordHolder: LiveData<String> = _passwordHolder
    private val _secondPasswordHolder = MutableLiveData("")
    val secondPasswordHolder: LiveData<String> = _secondPasswordHolder

    private val _emailErrorMessage = MutableLiveData<Int?>(null)
    val emailErrorMessage: LiveData<Int?> = _emailErrorMessage
    private val _passwordErrorMessage = MutableLiveData<Int?>(null)
    val passwordErrorMessage: LiveData<Int?> = _passwordErrorMessage
    private val _repeatPasswordErrorMessage = MutableLiveData<Int?>(null)
    val repeatPasswordErrorMessage: LiveData<Int?> = _repeatPasswordErrorMessage

    fun updateEmail(newEmail: String){
        _emailHolder.value = newEmail
    }

    fun updatePassword(newPassword: String){
        _passwordHolder.value = newPassword
    }

    fun updateSecondPassword(newSecondPassword: String){
        _secondPasswordHolder.value = newSecondPassword
    }

    fun onSignInButtonClicked(email: String = emailHolder.value.toString().lowercase(), password: String = passwordHolder.value.toString()){
        _emailErrorMessage.value = null
        _passwordErrorMessage.value = null
        _emailErrorMessage.value = checkEmailError(email)
        _passwordErrorMessage.value = checkPasswordError(password)
        if(_emailErrorMessage.value == null && _passwordErrorMessage.value == null) {
            loginUser(email, password)
        }
    }

    fun onRegisterButtonClicked(email: String = emailHolder.value.toString().lowercase(), password: String = passwordHolder.value.toString(), secondPassword: String = secondPasswordHolder.value.toString()){
        _emailErrorMessage.value = null
        _passwordErrorMessage.value = null
        _repeatPasswordErrorMessage.value = null
        _emailErrorMessage.value = checkEmailError(email)
        _passwordErrorMessage.value = checkPasswordError(password)
        _repeatPasswordErrorMessage.value = checkSecondPasswordError(password, secondPassword)
        if(emailErrorMessage.value == null && passwordErrorMessage.value == null && repeatPasswordErrorMessage.value == null){
            Log.d("Bayraktar", "registering")
            registerUser(email, password)
        }
    }

    private fun registerUser(email: String = emailHolder.value.toString().lowercase(), password: String = passwordHolder.value.toString()){
        viewModelScope.launch(Dispatchers.IO) {
            if (!userRepo.isEmailTaken(email)) {
                userRepo.insertUsers(User(0,null, password, email, System.currentTimeMillis(),null))
                loginUser(email, password)
            } else viewModelScope.launch{_emailErrorMessage.value = R.string.emailAlreadyTakenError}
        }
    }

    private fun loginUser(email: String = emailHolder.value.toString().lowercase(), password: String = passwordHolder.value.toString()){
        viewModelScope.launch(Dispatchers.IO){
            if(userRepo.doesUserExist(email, password)){
                viewModelScope.launch {
                    _logInEvent.value = LogInEvent(userRepo.getUserId(email, password))
                }
            } else viewModelScope.launch{_emailErrorMessage.value = R.string.incorrectPasswordOrEmailError}
        }
    }
}