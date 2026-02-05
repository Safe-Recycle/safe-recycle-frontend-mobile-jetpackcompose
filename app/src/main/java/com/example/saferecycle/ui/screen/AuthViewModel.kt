package com.example.saferecycle.ui.screen

import androidx.lifecycle.ViewModel
import com.example.saferecycle.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel(){

}