package com.intive.patronage22.intivi.ViewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
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

    fun updateEmail(newEmail: String){
        _emailHolder.value = newEmail
    }

    fun updatePassword(newPassword: String){
        _passwordHolder.value = newPassword
    }

    fun updateSecondPassword(newSecondPassword: String){
        _secondPasswordHolder.value = newSecondPassword
    }

    fun registerUser(email: String = _emailHolder.value.toString().lowercase(), password: String = _passwordHolder.value.toString()){
        viewModelScope.launch(Dispatchers.IO) {
            if (!userRepo.isEmailTaken(email)) {
                userRepo.insertUsers(User(0,null, password, email, System.currentTimeMillis(),null))
                loginUser(email, password)
            }
        }
    }

    fun loginUser(email: String = _emailHolder.value.toString().lowercase(), password: String = _passwordHolder.value.toString()){
        viewModelScope.launch(Dispatchers.IO){
            if(userRepo.doesUserExist(email, password)){
                viewModelScope.launch {
                    _logInEvent.value = LogInEvent(userRepo.getUserId(email, password))
                }
            }
        }
    }
}