package br.com.locaweb.components

import android.os.Build
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerButton(
    modifier: Modifier = Modifier,
    onDateSelected: (String) -> Unit,
    buttonBackgroundColor: Color = Color.Blue,
    buttonContentColor: Color = Color.White
) {
    var showCalendar by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("") }
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    Column(modifier = modifier) {
        Button(
            onClick = {
                showCalendar = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonBackgroundColor,
                contentColor = buttonContentColor,
            ),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(text = if (selectedDate.isEmpty()) "Selecionar Data" else selectedDate)
        }

        if (showCalendar) {
            Dialog(onDismissRequest = { showCalendar = false }) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                        .clickable { showCalendar = false },
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .background(Color.White, RoundedCornerShape(8.dp))
                            .clickable { /* No-op to prevent clicks from closing dialog */ }
                    ) {
                        AndroidView(
                            factory = { context ->
                                CalendarView(context).apply {
                                    setOnDateChangeListener { _, year, month, dayOfMonth ->
                                        val monthAdjusted = month + 1
                                        val date = LocalDate.of(year, monthAdjusted, dayOfMonth)
                                        selectedDate = date.format(dateFormatter)
                                        onDateSelected(selectedDate)
                                        showCalendar = false
                                    }
                                }
                            },
                            modifier = Modifier.wrapContentSize()
                        )
                    }
                }
            }
        }
    }
}
