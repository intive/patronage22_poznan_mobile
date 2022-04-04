package com.intive.patronage22.intivi.ViewModels

import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.EditText
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.*
import com.intive.patronage22.intivi.MainActivity
import com.intive.patronage22.intivi.database.User
import com.intive.patronage22.intivi.database.UserRepository
import com.intive.patronage22.intivi.isLoginFormValid
import com.intive.patronage22.intivi.isRegisterFormValid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(application: Application) : AndroidViewModel(application){

    private val userRepo = UserRepository()

    init{
        userRepo.initialize(getApplication<Application>().applicationContext)
    }

    private val _canLogIn = MutableLiveData(false)
    val canLogIn: LiveData<Boolean> = _canLogIn

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

    fun registerUser(){
        viewModelScope.launch(Dispatchers.IO) {
            viewModelScope.async {
                if (!userRepo.isEmailTaken(_emailHolder.value!!)) {
                    userRepo.insertUsers(
                        User(
                            0,
                            null,
                            _passwordHolder.value,
                            _emailHolder.value,
                            System.currentTimeMillis(),
                            null
                        )
                    )
                    loginUser(_emailHolder.value!!, _passwordHolder.value!!)
                }
            }
        }
    }

    fun loginUser(email: String, password: String){
        //TODO task 1659
    }
}