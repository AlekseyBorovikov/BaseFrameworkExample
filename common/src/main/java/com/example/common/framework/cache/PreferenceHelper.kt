package com.example.common.framework.cache

import android.content.Context
import android.content.SharedPreferences
import com.example.common.framework.data.Token
import javax.inject.Inject

/**
 * Allows you to manage variables in [SharedPreferences]
 * */
class PreferenceHelper @Inject constructor(context: Context, dbName: String) {
    private val appPrefs: SharedPreferences = context.getSharedPreferences(dbName, Context.MODE_PRIVATE)
    object PreferenceVariable {
        const val AUTH_TOKEN = "auth_token"
        const val AUTH_TOKEN_EXPIRY_TIME = "auth_token_expiry_time"
    }

    private val editor: SharedPreferences.Editor = appPrefs.edit()

    // Auth Token Setter/Getter
    var authToken: Token
        get() {
            val token = appPrefs.getString(PreferenceVariable.AUTH_TOKEN, "") ?: ""
            val expiryTime = appPrefs.getLong(PreferenceVariable.AUTH_TOKEN_EXPIRY_TIME, 0L)
            return if (token.isNotEmpty() || expiryTime != 0L) Token.Bearer(token, expiryTime) else Token.Empty
        }
        set(authToken) {
            editor.putString(PreferenceVariable.AUTH_TOKEN, authToken.token)
            editor.putLong(PreferenceVariable.AUTH_TOKEN_EXPIRY_TIME, authToken.expiryTime.timeInMillis)
            editor.apply()
        }

    init {
        editor.apply()
    }
}