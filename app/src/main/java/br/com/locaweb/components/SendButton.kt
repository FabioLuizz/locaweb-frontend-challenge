package br.com.locaweb.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.unit.dp
import androidx.compose.ui.input.pointer.pointerHoverIcon

@Composable
fun SendButton(
    modifier: Modifier = Modifier,
    onSendClick: () -> Unit,
    buttonBackgroundColor: Color = Color.Red,
    buttonContentColor: Color = Color.White
) {
    Button(
        onClick = onSendClick,
        modifier = Modifier
            .fillMaxWidth()
            .pointerHoverIcon(PointerIcon.Hand),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF0b3367),
        ),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(text = "Enviar E-mail")
    }
}
