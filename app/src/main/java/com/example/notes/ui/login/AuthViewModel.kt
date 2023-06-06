package com.example.notes.ui.login

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.models.UserRequest
import com.example.notes.models.UserResponse
import com.example.notes.repository.UserRepository
import com.example.notes.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val userResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() = userRepository.userResponseLiveData

    fun registerUser(userRequest: UserRequest){
        viewModelScope.launch {
            userRepository.registerUser(userRequest)
        }

    }
    fun loginUser(userRequest: UserRequest){
        viewModelScope.launch {
            userRepository.loginUser(userRequest)
        }

    }

    fun validateCredentials(userName: String,emailAddress : String , password: String,isLogin : Boolean): Pair<Boolean,String>{

        var result = Pair(true,"")

        if(!isLogin && TextUtils.isEmpty(userName) || TextUtils.isEmpty(emailAddress) || TextUtils.isEmpty(password) ){
            result = Pair(false,"Forneça as credenciais")

        }else if(Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
            result = Pair(false,"Forneça seu E-mail")

        }else if (password.length <=5){
            result = Pair(false,"O comprimento da senha deve ser maior que 5")

        }
        return  result

    }
}