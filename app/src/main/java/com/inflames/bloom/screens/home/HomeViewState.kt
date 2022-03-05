package com.inflames.bloom.screens.home

import com.inflames.bloom.data.Plant
import com.inflames.bloom.data.Checkable
import com.inflames.bloom.data.Theme

data class HomeViewState(
    val search: String = "",
    val themes: List<Theme> = listOf(),
    val plants: List<Checkable<Plant>> = listOf()
)