package br.com.locaweb.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.locaweb.R
import br.com.locaweb.api.RetrofitClient
import br.com.locaweb.components.Header
import br.com.locaweb.components.MailBoxDetails
import br.com.locaweb.model.EmailRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun EmailScreenLinkedinExample(navController: NavHostController, emailId: Long) {

    var emailState by remember { mutableStateOf<EmailRequest?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var showDialogForSpam by remember { mutableStateOf(false) }
    var context = LocalContext.current

    fun deleteEmail(emailId: Long) {
        val call = RetrofitClient.getServiceWithToken(context).updateStatus(emailId);
        call.enqueue(object : Callback<EmailRequest> {
            override fun onResponse(call: Call<EmailRequest>, response: Response<EmailRequest>) {
                if (response.isSuccessful) {
                    Log.d("EmailScreen", "Email movido para a lixeira")
                    // Ação após o sucesso, como navegar para outra tela
                } else {
                    Log.e("EmailScreen", "Erro ao mover o email para a lixeira: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<EmailRequest>, t: Throwable) {
                Log.e("EmailScreen", "Falha na requisição: ${t.message}")
            }
        })
    }

    fun SpamEmail(emailId: Long) {
        val call = RetrofitClient.getServiceWithToken(context).updateStatusForSpam(emailId);
        call.enqueue(object : Callback<EmailRequest> {
            override fun onResponse(call: Call<EmailRequest>, response: Response<EmailRequest>) {
                if (response.isSuccessful) {
                    Log.d("EmailScreen", "Email defino como spam")
                    // Ação após o sucesso, como navegar para outra tela
                } else {
                    Log.e("EmailScreen", "Erro ao definir email como spam: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<EmailRequest>, t: Throwable) {
                Log.e("EmailScreen", "Falha na requisição: ${t.message}")
            }
        })
    }

    LaunchedEffect(emailId) {
        val call = RetrofitClient.getServiceWithToken(context).getEmailsInbox(emailId)
        call.enqueue(object : Callback<EmailRequest> {
            override fun onResponse(call: Call<EmailRequest>, response: Response<EmailRequest>) {
                if (response.isSuccessful) {
                    emailState = response.body()
                } else {
                    Log.e("EmailScreen", "Erro ao carregar o email: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<EmailRequest>, t: Throwable) {
                Log.e("EmailScreen", "Falha na requisição: ${t.message}")
            }
        })
    }


    Column {
        Header(text = "Email")

        emailState?.let { email ->
            MailBoxDetails(
                emailId = email.id,
                name = "${email.user.name}",
                email = "${email.user.email}",
                date = "${email.date}",
                onDeleteClick = { showDialog = true },
                onSpamClick = { showDialogForSpam = true },
                imageRes = R.drawable.user_image
            )
        }


        Row(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(android.graphics.Color.parseColor("#dfdfdf")))
            .clip(RoundedCornerShape(4.dp))
        ) {

            emailState?.let { email ->
                Text(
                    text = "${email.subject}",
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                )
            }


        }

        if (showDialogForSpam) {
            AlertDialog(
                onDismissRequest = { showDialogForSpam = false },
                confirmButton = {
                    Button(onClick = {
                        showDialogForSpam = false
                        SpamEmail(emailId)
                        Log.d("EmailScreen", "Email confirmado para exclusão")
                        navController.navigate("MainScreen")
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                },
                title = { Text("Confirmar") },
                text = { Text("Tem certeza de que deseja definir esse email como spam ?") }
            )
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(onClick = {
                        showDialog = false
                        deleteEmail(emailId)
                        Log.d("EmailScreen", "Email confirmado para exclusão")
                        navController.navigate("MainScreen")
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                },
                title = { Text("Confirmar exclusão") },
                text = { Text("Tem certeza de que deseja mover este email para a lixeira?") }
            )
        }

    }
}
