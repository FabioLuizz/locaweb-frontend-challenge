package br.com.locaweb.model

import com.google.gson.annotations.SerializedName

data class UserRequest(

    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String

)
