package com.inflames.bloom.screens.bottomnav

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import com.inflames.bloom.screens.Screen

@Immutable
data class BottomNavModel(
    val icon: ImageVector,
    @StringRes val labelResourceId: Int,
    val screen: Screen
)