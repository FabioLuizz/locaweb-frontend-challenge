package br.com.locaweb.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.locaweb.R
import br.com.locaweb.api.RetrofitClient
import br.com.locaweb.components.Header
import br.com.locaweb.components.MailBox
import br.com.locaweb.model.EmailRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun SpamScreen(navController: NavController){

    var emailsState by remember { mutableStateOf<List<EmailRequest>>(emptyList()) }

    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    var context = LocalContext.current

    LaunchedEffect(Unit) {
        val call = RetrofitClient.getServiceWithToken(context).getEmailsByStatusAndSpam()
        call.enqueue(object : Callback<List<EmailRequest>> {
            override fun onResponse(
                call: Call<List<EmailRequest>>,
                response: Response<List<EmailRequest>>
            ) {
                if (response.isSuccessful) {
                    emailsState = response.body() ?: emptyList()
                    errorMessage = null
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Nenhum corpo de erro disponível"
                    errorMessage = "Erro ao buscar emails: ${response.code()} - ${response.message()} - $errorBody"
                }
                isLoading = false
                Log.i("Locaweb", "onResponse: ${response.body()}")
                Log.e("Locaweb", "Erro HTTP: ${response.code()} - ${response.message()}")
            }

            override fun onFailure(call: Call<List<EmailRequest>>, t: Throwable) {
                errorMessage = "Erro na comunicação com o servidor: ${t.message}"
                isLoading = false
                Log.e("Locaweb", "onFailure: ${t.message}", t)
            }
        })
    }

    Column {
        Header(text = "Caixa de Spam")

        Spacer(modifier = Modifier.height(6.dp))

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (errorMessage != null) {
            Text(
                text = errorMessage ?: "Erro desconhecido",
                modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyColumn {
                items(emailsState) { email ->
                    MailBox(
                        label = "${email.user.name}",
                        placeholder = "${email.subject}",
                        subTitle = "${email.user.email}",
                        onRowClick = { navController.navigate("EmailScreenLinkedinExample/${email.id}") },
                        imageRes = R.drawable.user_image,
                        isUnread = true
                    )
                }
            }
        }


    }

}