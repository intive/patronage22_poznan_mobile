package com.intive.patronage22.intivi.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){
    private var _tempTestString = MutableLiveData("This is a viewmodel value.")
    val tempTestString: LiveData<String> = _tempTestString

    var emailHolder: MutableLiveData<String> = MutableLiveData("")
    var passwordHolder: MutableLiveData<String> = MutableLiveData("")
    var secondPasswordHolder: MutableLiveData<String> = MutableLiveData("")
}