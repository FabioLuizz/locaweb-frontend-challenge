package br.com.locaweb.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header(
    text: String
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color(android.graphics.Color.parseColor("#696969")))
                .padding(16.dp)
        ) {
            Text(
                text = text,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 25.sp,
                color = androidx.compose.ui.graphics.Color.Red,
                textAlign = TextAlign.Center,
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }
    }
}