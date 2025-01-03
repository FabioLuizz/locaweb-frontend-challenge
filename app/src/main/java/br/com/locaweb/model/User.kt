package br.com.locaweb.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TB_USUARIO")
data class User (

    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var name: String = "",
    var email: String = "",
    var password: String = ""

)