package br.com.locaweb.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Domain
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.locaweb.components.Header
import br.com.locaweb.components.MainButton

@Composable
fun MainScreen(navController: NavController) {

    val scrollState = rememberScrollState()

    Box(){

        Header(text = "LOCAWEB")

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
                .padding(top = 60.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Novo E-mail",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 4.dp, start = 5.dp)
            )

            MainButton(onClick = { navController.navigate("MessageScreen") }, icon = Icons.Filled.Email)

            Text(
                text = "Companhias",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 4.dp, end = 10.dp)
            )

            MainButton(onClick = { navController.navigate("CompanyMailBoxes") }, icon = Icons.Filled.Domain)

            Text(
                text = "Lixeira",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 4.dp, start = 5.dp)
            )

            MainButton(onClick = { navController.navigate("ResourceScreen") }, icon = Icons.Filled.Delete)

            Text(
                text = "Caixa de Spam",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 4.dp, start = 5.dp)
            )

            MainButton(onClick = { navController.navigate("SpamScreen") }, icon = Icons.Filled.Delete)


            Text(
                text = "Enviados",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 4.dp, start = 5.dp)
            )

            MainButton(onClick = { navController.navigate("SentScreen") }, icon = Icons.Filled.Email)

            Text(
                text = "Conta",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 4.dp, start = 5.dp)
            )

            MainButton(onClick = { navController.navigate("ProfileScreen") }, icon = Icons.Filled.Person)

        }

    }
}