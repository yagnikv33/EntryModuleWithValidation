package com.example.unlocktechtask.server.apiservice

import com.example.unlocktechtask.server.model.LoginRequest
import com.example.unlocktechtask.server.model.LoginResponse
import com.example.unlocktechtask.server.model.OtpResponse
import com.example.unlocktechtask.server.model.VerifyOtpRequest
import com.example.unlocktechtask.server.model.VerifyOtpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/businessteamv1")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/retailergenerate_otp")
    suspend fun generateOtp(@Body request: LoginRequest): Response<OtpResponse>

    @POST("auth/retailercheck_otp")
    suspend fun verifyOtp(@Body request: VerifyOtpRequest): Response<VerifyOtpResponse>
}
