package br.com.locaweb.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun MainButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(100.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBBBBBB))
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(45.dp),
            tint = Color.White
        )
    }

    Spacer(modifier = Modifier.height(15.dp))
}