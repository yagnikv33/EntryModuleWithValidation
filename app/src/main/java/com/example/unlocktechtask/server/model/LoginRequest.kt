package com.example.unlocktechtask.server.model

data class LoginRequest(
    val deviceIMEI: String,
    val deviceSubscriptionId: String,
    val password: String,
    val username: String?,
    val version: Int
)
