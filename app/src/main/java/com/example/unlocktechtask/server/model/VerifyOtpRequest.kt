package com.example.unlocktechtask.server.model

data class VerifyOtpRequest(
    val deviceIMEI: String,
    val deviceSubscriptionId: String,
    val authToken: String?,
    val otp: String,
    val otpToken: String?,
    val password: String,
    val username: String?,
    val version: Int
)