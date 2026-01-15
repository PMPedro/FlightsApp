package com.example.flightsapp.Authentication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsapp.Authentication.data.UserRepository
import com.example.flightsapp.utils.Injection
import com.example.flightsapp.utils.Result
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch


class AuthViewModel : ViewModel() {
    private val userRepo: UserRepository

    init {
        userRepo = UserRepository(FirebaseAuth.getInstance(), Injection.instance())
    }

    private val _auth = MutableLiveData<Result<Boolean>>()
    val auth: LiveData<Result<Boolean>> = _auth


    fun signUp(email: String, password: String, username: String) {
        viewModelScope.launch {
            _auth.value = Result.Loading
            val result = try {
                userRepo.signUp(email, password, username)
            } catch (e: Exception) {
                Result.Error(e)
            }
            _auth.value = result
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _auth.value = Result.Loading
            val result = try {
                userRepo.login(email, password)
            } catch (e: Exception) {
                Result.Error(e)
            }
            _auth.value = result
        }
    }

}

