package com.intive.patronage22.intivi.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){
    var emailHolder: MutableLiveData<String> = MutableLiveData("")
    var passwordHolder: MutableLiveData<String> = MutableLiveData("")
    var secondPasswordHolder: MutableLiveData<String> = MutableLiveData("")
}