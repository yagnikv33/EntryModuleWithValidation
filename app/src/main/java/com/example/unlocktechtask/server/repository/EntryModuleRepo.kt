package com.example.unlocktechtask.server.repository

import com.example.unlocktechtask.server.model.LoginRequest
import com.example.unlocktechtask.server.model.VerifyOtpRequest
import com.example.unlocktechtask.server.apiservice.RetrofitService

class EntryModuleRepo {
    suspend fun login(request: LoginRequest) = RetrofitService.api.login(request)
    suspend fun generateOtp(request: LoginRequest) = RetrofitService.api.generateOtp(request)
    suspend fun verifyOtp(request: VerifyOtpRequest) = RetrofitService.api.verifyOtp(request)
}
