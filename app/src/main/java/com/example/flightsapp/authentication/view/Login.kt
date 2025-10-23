package com.example.flightsapp.authentication.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.flightsapp.authentication.viewmodel.AuthViewModel
import com.example.flightsapp.ui.theme.AppShapes
import com.example.flightsapp.ui.theme.AppSpacing
import com.example.flightsapp.utils.Result
import com.example.flightsapp.utils.WaveCanvas
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * This is a composable Function, used to deal with the Login/Welcome Screen.
 * This function has 2 arguments ,
 * @param navToSignUp Function to Navigate to SignUp
 * @param navOnLogonSucess Function to Navigate screens, after login its made with Sucess
 * @return Returns a Composable Login Screen
 * */
@Composable
fun LoginScreen(
    navToSignUp: () -> Unit,
    navOnLogonSucess: () -> Unit,
    viewModel: AuthViewModel
) {

    val config = LocalConfiguration.current
    val screenHeight = config.screenHeightDp.dp
    val showLogin = remember { mutableStateOf(false) }
    val waveProgress = remember { Animatable(0.5f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        var waveColor = MaterialTheme.colorScheme.primary
        WaveCanvas(waveProgress, screenHeight, waveColor)
        WelcomeContent(showLogin, waveProgress)
        LoginForm(showLogin, viewModel, navOnLogonSucess)
    }
}


@Composable
fun WelcomeContent(showLogin: MutableState<Boolean>, waveProgress: Animatable<Float, *>) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(AppSpacing.L),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        if (!showLogin.value) {
            Text(
                "Welcome",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(AppSpacing.M))
            Button(
                onClick =
                    {
                        scope.launch {
                            waveProgress.animateTo(
                                targetValue = 0.25f,
                                animationSpec = tween(
                                    durationMillis = 600,
                                    easing = FastOutSlowInEasing
                                )
                            )
                            showLogin.value = true
                        }
                    },
                shape = RoundedCornerShape(12.dp),
            ) {
                Text(
                    "Get Started",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun LoginForm(
    showLogin: MutableState<Boolean>,
    viewModel: AuthViewModel,
    onLoginSucess: () -> Unit
) {
    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var isLoading = remember { mutableStateOf(false) }
    val result by viewModel.auth.observeAsState()

    AnimatedVisibility(
        visible = showLogin.value,
        enter = fadeIn(animationSpec = tween(500)) +
                slideInVertically(initialOffsetY = { it / 2 }, animationSpec = tween(500))

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppSpacing.L),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(
                "Login", style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            LoginEmailTextField(email)
            Spacer(Modifier.padding(AppSpacing.M))
            LoginPasswordTextField(password)
            Spacer(Modifier.padding(AppSpacing.S))
            LoginSubmitButton(
                email = email,
                password = password,
                isLoading = isLoading,
                onLoginNavigate = {},
                checkLogin = {
                    viewModel.login(email = email.value, password = password.value)
                    when (result) {
                        is Result.Loading -> {
                            isLoading.value = true
                        }

                        is Result.Success -> {
                            onLoginSucess()
                        }

                        is Result.Error -> {
                            email.value = ""
                            password.value = ""
                        }

                        else -> {

                        }
                    }

                })
            Spacer(Modifier.padding(AppSpacing.M))
            Spacer(Modifier.padding(AppSpacing.L))
            Text(
                "Create an Account",
                modifier = Modifier.clickable {

                },
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


@Composable
fun LoginEmailTextField(email: MutableState<String>) {
    TextField(
        value = email.value,
        onValueChange = {
            email.value = it
        },
        placeholder = { Text("Email") },
        modifier = Modifier.fillMaxWidth()
    )

}

@Composable
fun LoginPasswordTextField(password: MutableState<String>) {
    TextField(
        value = password.value,
        onValueChange = {
            password.value = it
        },
        placeholder = { Text("Password") },
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun LoginSubmitButton(
    isLoading: MutableState<Boolean>,
    email: MutableState<String>,
    password: MutableState<String>,
    onLoginNavigate: () -> Unit,
    checkLogin: () -> Unit

) {
    Button(
        onClick = {
            isLoading.value = true
            //todo do login
            onLoginNavigate()

            isLoading.value = false
        }, modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        if (!isLoading.value) {
            Text(
                "Sign In",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        } else {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp),
                strokeWidth = 2.dp
            )
        }

    }
}


@Composable
@Preview
fun LoginPrev() {
    // LoginScreen({}, {})
    //LoginForm()
}