package br.com.locaweb.screens

import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.runtime.*
import androidx.navigation.NavController
import java.util.*

@Composable
fun CalendarViewComponent(
    navController: NavController,
    selectedDate: String?,
    onDateSelected: (String) -> Unit
) {
    var currentSelectedDate by remember { mutableStateOf(selectedDate) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable { /* Dismiss the calendar when clicking outside */ },
        )

        AndroidView(
            factory = { context ->
                CalendarView(context).apply {
                    setOnDateChangeListener { _, year, month, dayOfMonth ->
                        val date = "$dayOfMonth/${month + 1}/$year"
                        currentSelectedDate = date
                        onDateSelected(date)
                    }
                }
            },
            update = { view ->
                currentSelectedDate?.let { date ->
                    val parts = date.split("/")
                    if (parts.size == 3) {
                        val day = parts[0].toInt()
                        val month = parts[1].toInt() - 1
                        val year = parts[2].toInt()
                        val calendar = Calendar.getInstance().apply {
                            set(year, month, day)
                        }
                        view.date = calendar.timeInMillis
                    }
                }
            },
            modifier = Modifier.background(Color.White)
        )
    }
}