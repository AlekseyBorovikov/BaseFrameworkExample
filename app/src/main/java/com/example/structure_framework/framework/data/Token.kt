package com.example.structure_framework.framework.data

import com.example.structure_framework.framework.extension.utcToCalendar
import timber.log.Timber
import java.io.Serializable
import java.util.Calendar

/**
 * Instance for storing different types of token
 * */
sealed class Token(
    internal val token: String,
    expiryTime: Long,
): Serializable {

    object Empty : Token("", 0L)
    class Bearer(tokenValue: String, expiryTime: Long): Token(tokenValue, expiryTime) {
        override val tokenValue get() = "Bearer $token"
    }

    init { Timber.d("init token with value $token and expiryTime $expiryTime") }

    open val tokenValue get() = token

    val expiryTime: Calendar = expiryTime.utcToCalendar()

    override fun toString(): String {
        return "token: $token | expiryTime: $expiryTime"
    }
}