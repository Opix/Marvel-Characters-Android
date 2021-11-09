package net.opix.marvel.characters.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import net.opix.marvel.characters.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import net.opix.marvel.characters.extensions.md5Hash

object ServiceFactory {
    inline fun <reified T> createService(): T = retrofit(baseUrl = BuildConfig.BASE_URL).create(T::class.java)

    fun retrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttp())
        .build()

    private fun okHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })

        return builder.build()
    }

    private fun makeHash(): String {
        val timeStamp = System.currentTimeMillis().toString()

        return "$timeStamp${BuildConfig.MARVEL_PRIVATE_KEY}${BuildConfig.MARVEL_PUBLIC_KEY}".md5Hash
    }
}
