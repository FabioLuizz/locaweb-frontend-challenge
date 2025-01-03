package br.com.locaweb.api

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient

object RetrofitClient {

    private const val URL = "http://10.0.2.2:8080/" // Ajuste conforme necessário

    // Função para obter o OkHttpClient com suporte a JWT Token
    private fun getOkHttpClientWithToken(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()

                // Pegar o token JWT do SharedPreferences
                val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                val token = sharedPreferences.getString("jwt_token", null)

                if (token != null) {
                    requestBuilder.addHeader("Authorization", "Bearer $token")
                }

                chain.proceed(requestBuilder.build())
            }
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    fun getServiceWithToken(context: Context): ApiService {
        return Retrofit.Builder()
            .baseUrl(URL)
            .client(getOkHttpClientWithToken(context)) // Cliente com o interceptor JWT
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun getService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(URL)
            .client(getOkHttpClient()) // Cliente padrão, sem interceptor JWT
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}