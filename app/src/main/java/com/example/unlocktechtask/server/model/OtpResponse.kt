package com.example.unlocktechtask.server.model

data class OtpResponse(
    val resultCode: String,
    val resultStatus: String,
    val otpToken: String
)