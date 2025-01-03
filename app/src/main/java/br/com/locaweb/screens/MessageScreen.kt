package br.com.locaweb.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.locaweb.api.RetrofitClient
import br.com.locaweb.components.AttachmentButton
import br.com.locaweb.components.DatePickerButton
import br.com.locaweb.components.Header
import br.com.locaweb.components.MessageBox
import br.com.locaweb.components.LargeMessageBox
import br.com.locaweb.components.SendButton
import br.com.locaweb.model.EmailResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MessageScreen(navController: NavHostController) {

    var destino by remember { mutableStateOf("") }
    var assunto by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var selectedFile by remember { mutableStateOf<String?>(null) }

    var showCalendar by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }

    var isSnackbarVisible by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }

    val onDateSelected: (String) -> Unit = { date ->
        selectedDate = date
        showCalendar = false
    }



    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .fillMaxSize()) {
        Header(text = "Mensagem")

        Spacer(modifier = Modifier.height(5.dp))

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)) {

            Text(text = "Destino")

            MessageBox(
                label = "Destino",
                placeHolder = "Digite o e-mail do destinatario",
                value = destino,
                keyboardType = KeyboardType.Text,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                atualizarValor = {
                    destino = it
                }
            )

            Text(text = "Titulo")

            MessageBox(
                label = "Titulo",
                placeHolder = "Digite um titulo para seu e-mail",
                value = title,
                keyboardType = KeyboardType.Text,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                atualizarValor = {
                    title = it
                }
            )

            Text(text = "Assunto")

            LargeMessageBox(
                label = "Assunto",
                placeHolder = "Digite o que deseja enviar",
                value = assunto,
                keyboardType = KeyboardType.Text,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                height = 300,
                atualizarValor = {
                    assunto = it
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Data de Envio")

            DatePickerButton(
                onDateSelected = { date ->
                    selectedDate = date
                },
                buttonBackgroundColor = Color.Gray,
                buttonContentColor = Color.White,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            AttachmentButton(
                modifier = Modifier.fillMaxWidth(),
                onFileSelected = { filePath ->
                    selectedFile = filePath
                },
                buttonBackgroundColor = Color.Gray,
                buttonContentColor = Color.White
            )

            selectedFile?.let {
                Text(text = "Arquivo Selecionado: $it")
            }

            Spacer(modifier = Modifier.height(16.dp))

            SendButton(
                modifier = Modifier.fillMaxWidth(),
                onSendClick = {
                    CoroutineScope(Dispatchers.Main).launch {
                        val success = sendEmail(
                            context = navController.context,
                            destino = destino,
                            title = title,
                            assunto = assunto,
                            selectedDate = selectedDate
                        )

                        if (success) {
                            snackbarHostState.showSnackbar(
                                message = "Email enviado com sucesso!",
                                actionLabel = "OK"
                            ).let { result ->
                                if (result == SnackbarResult.ActionPerformed) {
                                    navController.navigate("mainScreen") {
                                        popUpTo("MessageScreen") { inclusive = true }
                                    }
                                }
                            }
                        } else {
                            snackbarHostState.showSnackbar(
                                message = "Erro ao enviar e-mail. Tente novamente.",
                                actionLabel = "OK"
                            )
                        }
                        isSnackbarVisible = true
                    }
                },
                buttonBackgroundColor = Color.Red,
                buttonContentColor = Color.White
            )

        }

    }

    if (showCalendar) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable { showCalendar = false },
            )

            CalendarViewComponent(navController = navController, selectedDate = selectedDate, onDateSelected = { date ->
                onDateSelected(date)
                showCalendar = false
            })
        }
    }

    SnackbarHost(hostState = snackbarHostState)

}

suspend fun sendEmail(
    context: Context,
    destino: String,
    title: String,
    assunto: String,
    selectedDate: String
): Boolean {

    val emailResponse = EmailResponse(
        destination = destino,
        title = title,
        subject = assunto,
        date = selectedDate
    )

    val apiService = RetrofitClient.getServiceWithToken(context)
    return try {
        val response = apiService.sendEmail(emailResponse)
        response.isSuccessful
    } catch (e: Exception) {
        false
    }

}