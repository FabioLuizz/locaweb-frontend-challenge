package br.com.locaweb.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.locaweb.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val userName = remember { "Nome do Usuário" }
    val userEmail = remember { "usuario@example.com" }
    val userPassword = remember { "********" }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.user_image),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .background(Color.Gray, shape = CircleShape)
                .padding(2.dp)
                .background(Color.White, shape = CircleShape)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = userName,
            onValueChange = {},
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth(0.9f),
            enabled = false,
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = Color.Black,
                disabledLabelColor = Color.Gray,
                disabledIndicatorColor = Color.Gray,
                disabledPlaceholderColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = userEmail,
            onValueChange = {},
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(0.9f),
            enabled = false,
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = Color.Black,
                disabledLabelColor = Color.Gray,
                disabledIndicatorColor = Color.Gray,
                disabledPlaceholderColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = userPassword,
            onValueChange = {},
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth(0.9f),
            enabled = false,
            visualTransformation = VisualTransformation.None,
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = Color.Black,
                disabledLabelColor = Color.Gray,
                disabledIndicatorColor = Color.Gray,
                disabledPlaceholderColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Text("Alterar", fontSize = 16.sp, color = Color.Black)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { navController.navigate("loginScreen") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0b3367)),
            modifier = Modifier.fillMaxWidth(0.9f).height(40.dp)
        ) {
            Text("Sair", fontSize = 16.sp, color = Color.White)
        }
    }
}