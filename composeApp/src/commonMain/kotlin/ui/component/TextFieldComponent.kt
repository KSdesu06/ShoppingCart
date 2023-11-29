package ui.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
    fun TextFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: @Composable () -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                textColor = Color.Black,
                cursorColor = Color.White,
                backgroundColor = Color.White
            ),
            keyboardOptions = keyboardOptions
        )
    }