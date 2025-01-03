package br.com.locaweb.components

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon


@Composable
fun AttachmentButton(
    modifier: Modifier = Modifier,
    onFileSelected: (String) -> Unit,
    buttonBackgroundColor: Color = Color.Red,
    buttonContentColor: Color = Color.White
) {
    val selectedFilePath = remember { mutableStateOf<String?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            // Processar o URI do arquivo aqui
            selectedFilePath.value = it.toString()
            onFileSelected(it.toString())
        }
    }

    Column(modifier = modifier) {
        Button(
            onClick = {
                launcher.launch("*/*")
            },
            modifier = Modifier.fillMaxWidth().pointerHoverIcon(PointerIcon.Hand),
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonBackgroundColor,
                contentColor = buttonContentColor,
            ),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(text = "Anexar Arquivo")
        }

        selectedFilePath.value?.let {
            Text(text = "Arquivo selecionado: $it")
        }
    }
}
