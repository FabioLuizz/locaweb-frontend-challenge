package br.com.locaweb.screens

import android.content.Context
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
import androidx.navigation.NavController
import br.com.locaweb.R
import br.com.locaweb.api.RetrofitClient
import br.com.locaweb.model.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun SigninScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    var context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.width(300.dp)
            .height(200.dp)
            .align(Alignment.TopCenter)
            .offset(0.dp, 40.dp)

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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
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

            Button(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {

                        GlobalScope.launch(Dispatchers.Main) {
                            try {

                                val loginRequest = LoginRequest(email = email, password = password)
                                val response = RetrofitClient.getService().loginUser(loginRequest)

                                if (response.isSuccessful) {
                                    val tokenResponse = response.body()

                                    tokenResponse?.token?.let { token ->
                                        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                                        with(sharedPreferences.edit()) {
                                            putString("jwt_token", token)
                                            apply()
                                        }

                                        navController.navigate("mainScreen")

                                    }

                                } else {
                                    errorMessage =
                                        "Credenciais inválidas. Por favor, tente novamente."
                                    showError = true
                                }
                            } catch (e: Exception) {
                                errorMessage =
                                    "Ocorreu um erro ao tentar fazer o login. Por favor, tente novamente mais tarde."
                                showError = true
                            }
                        }
                    } else {
                        errorMessage = "Por favor, preencha todos os campos."
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0b3367)),
                modifier = Modifier.fillMaxWidth(0.9f).height(40.dp)
            ) {
                Text("Login", fontSize = 16.sp, color = Color.White)
            }

            if (showError) {
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    action = {
                        Button(
                            onClick = { showError = false }, // Esconde o Snackbar
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                        ) {
                            Text("OK", color = Color.Black)
                        }
                    }
                ) {
                    Text(errorMessage, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(
                onClick = {navController.navigate("RegisterScreen")},
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Text("Registrar", fontSize = 16.sp)
            }
        }
    }
}