package com.intive.patronage22.intivi.ViewModels

import android.app.Application
import androidx.lifecycle.*
import com.intive.patronage22.intivi.database.User
import com.intive.patronage22.intivi.database.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application){

    private val userRepo = UserRepository()

    //TODO dejanuszify passing context into viewmodel
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
        val email = _emailHolder.value.toString()
        val password = _passwordHolder.value.toString()
        viewModelScope.launch(Dispatchers.IO) {
            viewModelScope.async {
                if (!userRepo.isEmailTaken(email)) {
                    userRepo.insertUsers(
                        User(
                            0,
                            null,
                            password,
                            email,
                            System.currentTimeMillis(),
                            null
                        )
                    )
                    loginUser(email, password)
                }
            }
        }
    }

    fun loginUser(email: String = _emailHolder.value.toString(), password: String = _passwordHolder.value.toString()){
        viewModelScope.launch(Dispatchers.IO){
            viewModelScope.async{
                if(userRepo.doesUserExist(email, password)){
                    _canLogIn.value = true
                }
            }
        }
    }
}