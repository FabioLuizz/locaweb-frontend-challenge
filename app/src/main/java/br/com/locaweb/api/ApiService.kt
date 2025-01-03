package br.com.locaweb.api

import br.com.locaweb.model.EmailRequest
import br.com.locaweb.model.EmailResponse
import br.com.locaweb.model.LoginRequest
import br.com.locaweb.model.TokenResponse
import br.com.locaweb.model.RegisterResponse
import br.com.locaweb.model.UserRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST("locaweb/register")
    suspend fun registerUser(@Body userRequest: UserRequest): Response<RegisterResponse>

    @POST("locaweb/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<TokenResponse>

    @POST("email/send")
    suspend fun sendEmail(@Body emailResponse: EmailResponse): Response<Void>

    @GET("email/user")
    fun getSentEmails(): Call<List<EmailRequest>>

    @GET("email/inbox")
    fun getEmails(): Call<List<EmailRequest>>

    @GET("email/inbox/{id}")
    fun getEmailsInbox(@Path("id") id: Long): Call<EmailRequest>

    @GET("email/trash")
    fun getEmailsByStatus(): Call<List<EmailRequest>>

    @PUT("email/inbox/{id}")
    fun updateStatus(@Path("id") id: Long): Call<EmailRequest>

    @GET("email/spam")
    fun getEmailsByStatusAndSpam(): Call<List<EmailRequest>>

    @PUT("email/inbox/{id}/spam")
    fun updateStatusForSpam(@Path("id") id: Long): Call<EmailRequest>
}