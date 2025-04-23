package com.example.unlocktechtask.server.model

data class VerifyOtpResponse(
    val resultCode: String,
    val resultStatus: String,
    val resultMessage: String,
    val ForcePasswordUpdate: String?,
    val authToken: String?
)