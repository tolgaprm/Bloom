package com.inflames.bloom.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.inflames.bloom.R

val FontFamily.Companion.NunitoSans
    get() = FontFamily(
        Font(R.font.nunito_sans_light, FontWeight.Light),
        Font(R.font.nunito_sans_semi_bold, FontWeight.SemiBold),
        Font(R.font.nunito_sans_bold, FontWeight.Bold)
    )

val BloomTheme.typography
    get() = Typography(
        defaultFontFamily = FontFamily.NunitoSans,
        h1 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            letterSpacing = 0.sp,
        ),
        h2 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            letterSpacing = .15.sp,
        ),
        subtitle1 = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            letterSpacing = 0.sp,
        ),
        body1 = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 14.sp,
            letterSpacing = 0.sp,
        ),
        body2 = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            letterSpacing = 0.sp,
        ),
        button = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            letterSpacing = 1.sp,
        ),
        caption = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            letterSpacing = 0.sp,
        ),
    )
