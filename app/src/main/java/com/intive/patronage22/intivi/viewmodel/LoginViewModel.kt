package com.intive.patronage22.intivi.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.intive.patronage22.intivi.LoginValidation.checkEmailError
import com.intive.patronage22.intivi.LoginValidation.checkPasswordError
import com.intive.patronage22.intivi.LoginValidation.checkSecondPasswordError
import com.intive.patronage22.intivi.R
import com.intive.patronage22.intivi.api.ApiClient
import com.intive.patronage22.intivi.database.UserRepository
import com.intive.patronage22.intivi.model.LogInEvent
import com.intive.patronage22.intivi.model.SignInResponse
import com.intive.patronage22.intivi.model.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel(){

    lateinit var userRepo: UserRepository

    private val _logInEvent = MutableLiveData(LogInEvent(false, null))
    val logInEvent: LiveData<LogInEvent> = _logInEvent

    private val _emailErrorMessage = MutableLiveData<Int?>(null)
    val emailErrorMessage: LiveData<Int?> = _emailErrorMessage
    private val _passwordErrorMessage = MutableLiveData<Int?>(null)
    val passwordErrorMessage: LiveData<Int?> = _passwordErrorMessage
    private val _repeatPasswordErrorMessage = MutableLiveData<Int?>(null)
    val repeatPasswordErrorMessage: LiveData<Int?> = _repeatPasswordErrorMessage

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
            registerUser(email, password)
        }
    }

    private fun loginUser(email: String, password: String){
        ApiClient().getService()?.signIn(email, password)
            ?.enqueue(object : Callback<SignInResponse> {

                override fun onResponse(call: Call<SignInResponse>, response: Response<SignInResponse>) {
                    val loginResponse = response.body()
                    if(response.isSuccessful) {
                        if (loginResponse?.token != null) {
                            _logInEvent.value = LogInEvent(true, loginResponse.token)
                        }
                    }
                    else {
                        assignFullError(R.string.incorrectPasswordOrEmailError)
                    }
                }
                override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                    assignFullError(R.string.serverConnectionFailedError)
                }
            })
    }

    private fun registerUser(email: String, password: String){
        ApiClient().getService()?.signUp(email, email, password)
            ?.enqueue(object : Callback<SignUpResponse>{
                override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                    if(response.isSuccessful) {
                        loginUser(email,password)
                    }
                    else {
                        assignFullError(R.string.incorrectPasswordOrEmailError)
                    }
                }
                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    assignFullError(R.string.serverConnectionFailedError)
                }
            })
    }

    private fun assignFullError(errorMessage: Int?){
        _emailErrorMessage.value = errorMessage
        _passwordErrorMessage.value = errorMessage
    }
}