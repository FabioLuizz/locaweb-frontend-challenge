package br.com.locaweb.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FilterButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonBackgroundColor: Color = Color.Red,
    buttonContentColor: Color = Color.White
) {

    Spacer(modifier = Modifier.height(16.dp))

    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF0b3367)
        ),
        shape = RoundedCornerShape(50.dp)
    ) {
        Text(text = "Filtrar por Data")
    }
}