package com.inflames.bloom.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BloomTextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {

    BloomButtonTheme {
        TextButton(
            onClick = onClick,
            modifier = Modifier.defaultMinSize(minHeight = 48.dp),
            shape = MaterialTheme.shapes.medium,
            content = content
        )
    }
}

