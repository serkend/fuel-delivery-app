package com.example.geeksforgeekschat.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {

    fun isUserAuthenticated() = authUseCases.isAuthenticated(viewModelScope)

}