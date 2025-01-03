package br.com.locaweb.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.Log
import androidx.navigation.NavController
import br.com.locaweb.R
import br.com.locaweb.api.RetrofitClient
import br.com.locaweb.model.UserRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var errorMessage by remember { mutableStateOf<String?>(null) }

    var context = LocalContext.current


    Box {

        Box(
            modifier = Modifier.width(300.dp)
                .height(200.dp)
                .align(Alignment.TopCenter)
                .offset(0.dp, 30.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth(0.9f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(0.9f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Senha") },
                modifier = Modifier.fillMaxWidth(0.9f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirme a senha") },
                modifier = Modifier.fillMaxWidth(0.9f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if(password == confirmPassword) {
                        val userRequest = UserRequest(name = name, email = email, password = password)

                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                val response = RetrofitClient.getService().registerUser(userRequest)

                                if (response.isSuccessful) {

                                    errorMessage = "Cadastro concluído com sucesso!"
                                } else {
                                    errorMessage = "Erro ao cadastrar. Por favor tente novamente."
                                }
                            } catch (e: Exception) {
                                errorMessage = "Erro de rede: ${e.message}"
                            }
                        }
                    } else {
                        errorMessage =
                            "As senhas não correspondem. Por favor tente novamente"
                        password = ""
                        confirmPassword = ""
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0b3367)),
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Text("Registrar", fontSize = 16.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(
                onClick = {navController.navigate("SigninScreen")},
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Text("Login", fontSize = 16.sp)
            }

            errorMessage?.let { message ->
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    action = {
                        Button(
                            onClick = { errorMessage = null },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                        ) {
                            Text("OK", color = Color.Black)
                        }
                    }
                ) {
                    Text(message, color = Color.White)
                }
            }
        }
    }
}
