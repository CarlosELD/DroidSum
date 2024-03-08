package com.example.droidsum.data

import android.annotation.SuppressLint
import android.content.Context
import androidx.preference.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

@Suppress("DEPRECATION")
class RecibirCookie
    (private val context: Context) : Interceptor {
    @SuppressLint("MutatingSharedPrefs")
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse: Response = chain.proceed(chain.request())
        if (originalResponse.headers("Set-Cookie").isNotEmpty()) {
            val cookies = PreferenceManager.getDefaultSharedPreferences(
                context
            ).getStringSet("PREF_COOKIES", HashSet()) as HashSet<String>?
            for (header in originalResponse.headers("Set-Cookie")) {
                cookies!!.add(header)
            }
            val memes = PreferenceManager.getDefaultSharedPreferences(
                context
            ).edit()
            memes.putStringSet("PREF_COOKIES", cookies).apply()
            memes.commit()
        }
        return originalResponse
    }
}