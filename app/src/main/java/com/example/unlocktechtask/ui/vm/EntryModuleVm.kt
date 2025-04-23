package com.example.unlocktechtask.ui.vm

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unlocktechtask.server.model.LoginRequest
import com.example.unlocktechtask.server.model.LoginResponse
import com.example.unlocktechtask.server.model.OtpResponse
import com.example.unlocktechtask.server.model.VerifyOtpRequest
import com.example.unlocktechtask.server.model.VerifyOtpResponse
import com.example.unlocktechtask.server.repository.EntryModuleRepo
import kotlinx.coroutines.launch

class EntryModuleVm : ViewModel() {

    private val repo = EntryModuleRepo()

    val loginResult = MutableLiveData<LoginResponse>()
    val otpResult = MutableLiveData<OtpResponse>()
    val verifyOtpResult = MutableLiveData<VerifyOtpResponse>()
    val error = MutableLiveData<String>()

    val isLoading = MutableLiveData(false)

    fun isValidMobile(mobile: String): Boolean {
        return Regex("^\\d{10}$").matches(mobile)
    }

    fun isValidOtp(mobile: String): Boolean {
        return Regex("^.{7,}\$").matches(mobile)
    }

    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun login(request: LoginRequest) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repo.login(request)
                if (response.isSuccessful) {
                    loginResult.postValue(response.body())
                } else {
                    error.postValue("Login failed: ${response.code()}")
                }
            } catch (e: Exception) {
                error.postValue("Login error: ${e.message}")
            } finally {
                isLoading.value = false
            }
        }
    }

    fun generateOtp(request: LoginRequest) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repo.generateOtp(request)
                if (response.isSuccessful) {
                    otpResult.postValue(response.body())
                } else {
                    error.postValue("OTP failed: ${response.code()}")
                }
            } catch (e: Exception) {
                error.postValue("OTP error: ${e.message}")
            } finally {
                isLoading.value = false
            }
        }
    }

    fun verifyOtp(request: VerifyOtpRequest) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repo.verifyOtp(request)
                if (response.isSuccessful) {
                    verifyOtpResult.postValue(response.body())
                } else {
                    error.postValue("Verify failed: ${response.code()}")
                }
            } catch (e: Exception) {
                error.postValue("Verify error: ${e.message}")
            } finally {
                isLoading.value = false
            }
        }
    }
}