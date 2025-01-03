package br.com.locaweb.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeMessageBox(
    label: String,
    placeHolder: String,
    value: String,
    keyboardType: KeyboardType,
    modifier: Modifier,
    height: Int = 200,
    atualizarValor: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = atualizarValor,
        modifier = modifier.height(height.dp),
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeHolder)
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        maxLines = 10
    )
}
