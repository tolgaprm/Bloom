package com.inflames.bloom.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.inflames.bloom.R
import com.inflames.bloom.components.BloomButton
import com.inflames.bloom.components.BloomTextButton

var  a = true

@Composable
fun WelcomeScreen(navController: NavHostController) {


    Surface(color = MaterialTheme.colors.primary, modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.welcome_bg), contentDescription = null,
            contentScale = ContentScale.FillWidth
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(72.dp))


            Image(
                painter = painterResource(id = R.drawable.welcome_illos),
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 88.dp),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(48.dp))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(
                    R.string.app_logo_content_description
                )
            )

            Text(
                text = stringResource(id = R.string.welcome_subtitle),
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.paddingFromBaseline(32.dp, bottom = 40.dp)
            )


            BloomButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(
                    text = "Create account",
                    style = MaterialTheme.typography.button
                )
            }

            BloomTextButton(onClick = { navController.navigate(Screen.Login.route) }) {
                Text(text = "Login")
            }
        }
    }


}