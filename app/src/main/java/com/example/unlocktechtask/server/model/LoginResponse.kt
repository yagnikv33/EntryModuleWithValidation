package com.example.unlocktechtask.server.model

data class LoginResponse(
    val resultCode: String,
    val resultStatus: String,
    val BusinessName: String?,
    val IdentificationCode: String?,
    val Mobile: String?,
    val authToken: String?,
    val ExpiryIn: Double?
)