package br.com.locaweb.model

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String

)
