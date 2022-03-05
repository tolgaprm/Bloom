package com.inflames.bloom.screens.loginscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.inflames.bloom.R
import com.inflames.bloom.components.BloomButton
import com.inflames.bloom.components.BloomOutlinedTextField
import com.inflames.bloom.screens.Screen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginScreenViewModel = viewModel()
) {

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.login_with_email),
                style = MaterialTheme.typography.h1,
                modifier = Modifier.paddingFromBaseline(184.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            BloomOutlinedTextField(
                value = loginViewModel.loginScreenState.value.email,
                onValueChange = loginViewModel::onChangeEmail,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = stringResource(R.string.loginscreen_email)) },
                textStyle = MaterialTheme.typography.body1,
                isError = loginViewModel.loginScreenState.value.emailAddressError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true
            )
            AnimatedVisibility(loginViewModel.loginScreenState.value.emailAddressError) {
                Text(
                    text = stringResource(R.string.login_invalid_email),
                    color = MaterialTheme.colors.error
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            BloomOutlinedTextField(
                value = loginViewModel.loginScreenState.value.password,
                onValueChange = loginViewModel::onChangePassword,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = stringResource(R.string.login_screen_password)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                textStyle = MaterialTheme.typography.body1,
                singleLine = true,
                isError = loginViewModel.loginScreenState.value.passwordError,
                visualTransformation = PasswordVisualTransformation()
            )
            AnimatedVisibility(loginViewModel.loginScreenState.value.passwordError) {
                Text(
                    text = stringResource(R.string.login_invalid_password),
                    color = MaterialTheme.colors.error
                )
            }

            val termsText = buildAnnotatedString {
                append(stringResource(id = R.string.login_terms_1))

                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                    append(" ")
                    append(stringResource(id = R.string.login_terms_2))
                    append(" ")
                }

                append(stringResource(id = R.string.login_terms_3))

                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                    append(" ")
                    append(stringResource(id = R.string.login_terms_4))
                    append("")
                }
            }

            Text(
                text = termsText,
                modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2
            )

            BloomButton(
                onClick = {
                    loginViewModel.onLoginClick()
                    if (loginViewModel.viewEvents.value) {
                        navController.navigate(Screen.Home.route) {
                            launchSingleTop = true
                            // Home Sayfasından geri tuşuna basıldığında welcome sayfasına git ve inclusive true diyerek gittiği o sayfadan da geriye gitmesini sölüyoruz
                            // Ama başka bir sayfa olmadığından uygulamadan çıkar
                            popUpTo(Screen.Welcome.route) {
                                inclusive = true
                            }

                        }
                        loginViewModel.navigatedFinish()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.login_login))
            }
        }
    }


}