package br.com.locaweb.components

import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import java.util.*

@Composable
fun FilterScreen(navController: NavHostController) {
    var selectedDate by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Selecione uma Data",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            AndroidView(
                factory = { context ->
                    CalendarView(context).apply {
                        setOnDateChangeListener { _, year, month, dayOfMonth ->
                            val monthAdjusted = month + 1
                            val date = "$dayOfMonth/$monthAdjusted/$year"
                            selectedDate = date
                        }
                    }
                },
                modifier = Modifier.wrapContentSize()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.previousBackStackEntry?.savedStateHandle?.set("selectedDate", selectedDate)
                    navController.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0b3367)
                ),
                shape = RoundedCornerShape(50.dp) // Borda arredondada
            ) {
                Text(text = "Confirmar")
            }
        }
    }
}