package br.com.locaweb.model

import com.google.gson.annotations.SerializedName

data class EmailRequest (

    @SerializedName("id") val id: Long,
    @SerializedName("destination") val destination: String,
    @SerializedName("title") val title: String,
    @SerializedName("subject") val subject: String,
    @SerializedName("date") val date: String,
    @SerializedName("user") val user: User

)