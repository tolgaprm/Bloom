package com.inflames.bloom.screens.loginscreen

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginScreenViewModel : ViewModel() {

    private val _loginScreenState = mutableStateOf<LoginViewState>(LoginViewState())
    val loginScreenState: State<LoginViewState> get() = _loginScreenState

    private val _viewEvents = mutableStateOf<Boolean>(false)
    val viewEvents: State<Boolean> get() = _viewEvents

    fun onChangeEmail(value: String) {
        _loginScreenState.value = _loginScreenState.value.copy(
            email = value,
            emailAddressError = false
        )
    }

    fun onChangePassword(value: String) {
        _loginScreenState.value = _loginScreenState.value.copy(
            password = value,
            passwordError = false
        )

    }

    fun onLoginClick() {

        val isValidEmail = validateEmail()
        val isValidPassword = validatePassword()

        if (isValidEmail && isValidPassword) {
            _viewEvents.value = true
        }

    }

    fun navigatedFinish() {
        _viewEvents.value = false
    }

    private fun validateEmail(): Boolean {
        val email = _loginScreenState.value.email

        val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        if (isValidEmail.not()) {
            _loginScreenState.value = _loginScreenState.value.copy(
                emailAddressError = true
            )
        }

        return isValidEmail
    }

    private fun validatePassword(): Boolean {
        val password = _loginScreenState.value.password

        val passwordValid = password.length >= 8

        if (passwordValid) {
            _loginScreenState.value = _loginScreenState.value.copy(
                passwordError = false
            )
        } else {

            _loginScreenState.value = _loginScreenState.value.copy(
                passwordError = true
            )
        }

        return passwordValid
    }

}