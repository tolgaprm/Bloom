package com.inflames.bloom.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import com.inflames.bloom.ui.theme.BloomTheme
import com.inflames.bloom.ui.theme.shapes
import com.inflames.bloom.ui.theme.typography

@Composable
fun BloomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    textStyle: TextStyle = LocalTextStyle.current,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false
) {
    BloomOutlinedTextFieldTheme() {
        OutlinedTextField(
            value = value, onValueChange = onValueChange,
            leadingIcon = leadingIcon,
            modifier = modifier,
            placeholder = placeholder,
            textStyle = textStyle,
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            visualTransformation = visualTransformation,
            isError = isError
        )

    }

}

@Composable
fun BloomOutlinedTextFieldTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) {
        BloomTheme.darkColors.copy(
            primary = BloomTheme.darkColors.onPrimary,
        )
    } else {
        BloomTheme.lightColors.copy(
            primary = BloomTheme.lightColors.onPrimary,
        )
    }

    MaterialTheme(
        colors = colors,
        typography = BloomTheme.typography,
        shapes = BloomTheme.shapes,
        content = content,
    )
}