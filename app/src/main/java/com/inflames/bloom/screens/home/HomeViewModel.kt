package com.inflames.bloom.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.inflames.bloom.data.Checkable
import com.inflames.bloom.data.MockData
import com.inflames.bloom.data.Plant

class HomeViewModel : ViewModel() {

    private val _homeViewState = mutableStateOf<HomeViewState>(HomeViewState())
    val homeViewState: State<HomeViewState> get() = _homeViewState


    init {
        _homeViewState.value = _homeViewState.value.copy(
            themes = MockData.themes,
            plants = MockData.plants.map { Checkable(it) }
        )
    }

    fun onChangeSearch(value: String) {
        _homeViewState.value = _homeViewState.value.copy(
            search = value
        )
    }

    fun onCheckedChange(plant: Plant, checked: Boolean) {
        val plants = _homeViewState.value.plants.map { checkablePlant ->
            if (checkablePlant.data.name == plant.name) {
                checkablePlant.copy(
                    checked = checked
                )
            } else {
                checkablePlant
            }
        }
        _homeViewState.value = _homeViewState.value.copy(
            plants = plants
        )

    }
}