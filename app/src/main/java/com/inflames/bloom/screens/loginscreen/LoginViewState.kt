package com.inflames.bloom.screens.loginscreen

data class LoginViewState(
    val email: String = "",
    val emailAddressError: Boolean = false,
    val password: String = "",
    val passwordError: Boolean = false
)