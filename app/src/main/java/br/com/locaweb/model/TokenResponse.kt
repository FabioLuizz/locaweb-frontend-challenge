package br.com.locaweb.model

import com.google.gson.annotations.SerializedName

data class TokenResponse(

    @SerializedName("token") val token: String, // O token JWT

)
