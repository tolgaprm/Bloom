package com.inflames.bloom.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.inflames.bloom.ui.theme.BloomTheme
import com.inflames.bloom.ui.theme.shapes
import com.inflames.bloom.ui.theme.typography

@Composable
fun BloomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {

    BloomButtonTheme {

        Button(
            onClick = onClick,
            modifier = modifier
                .defaultMinSize(minHeight = 48.dp),
            shape = MaterialTheme.shapes.medium,
            content = content
        )
    }
}

@Composable
fun BloomButtonTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = if (darkTheme) {
        BloomTheme.darkColors.copy(
            primary = BloomTheme.darkColors.secondary,
            onPrimary = BloomTheme.darkColors.onSecondary,
        )
    } else {
        BloomTheme.lightColors.copy(
            primary = BloomTheme.lightColors.secondary,
            onPrimary = BloomTheme.lightColors.onSecondary,
        )
    }

    MaterialTheme(
        colors = colors,
        typography = BloomTheme.typography,
        shapes = BloomTheme.shapes,
        content = content,
    )
}