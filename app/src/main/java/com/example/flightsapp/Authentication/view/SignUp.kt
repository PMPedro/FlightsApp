package com.example.flightsapp.authentication.view

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flightsapp.authentication.viewmodel.AuthViewModel
import com.example.flightsapp.ui.theme.AppElevation
import com.example.flightsapp.ui.theme.AppSpacing
import com.example.flightsapp.utils.WaveCanvas


//TODO Add variables to hold the data, create the account, add loading status , lock textFields after clicking submit
@Composable
fun SignUpScreen(
    navToLogin: () -> Unit,
    navOnSignUpSucess: () -> Unit,
    viewModel : AuthViewModel
) {
    val waveProgress = remember { Animatable(0.5f) }
    val config = LocalConfiguration.current
    val screenHeight = config.screenHeightDp.dp
    var waveColor = MaterialTheme.colorScheme.primary
    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var username = remember { mutableStateOf("") }
    var isLoading = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        WaveCanvas(waveProgress, screenHeight, waveColor)
       SignUpForm(
           email = email ,
           password = password ,
           username = username ,
           isLoading = isLoading ,
           onClickSubmitButton = {},
           onClickAlreadyHasAccountButton = {}
       )
    }


}

@Composable
fun SignUpForm(
    email: MutableState<String>,
    password: MutableState<String>,
    username: MutableState<String>,
    isLoading: MutableState<Boolean>,
    onClickSubmitButton: () -> Unit,
    onClickAlreadyHasAccountButton: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(AppSpacing.L),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        EmailTextField(email)
        Spacer(Modifier.padding(AppSpacing.M))
        PasswordTextField(password)
        Spacer(Modifier.padding(AppSpacing.M))
        UsernameTextField(username)
        Spacer(modifier = Modifier.padding(AppSpacing.L))
        SubmitButton(isLoading, onClickSubmitButton)
        Spacer(modifier = Modifier.padding(AppSpacing.S))
        AlreadyHasAccountText(onClickAlreadyHasAccountButton)

    }
}

@Composable
fun EmailTextField(email: MutableState<String>) {
    TextField(
        value = email.value,
        onValueChange = { email.value = it },
        placeholder = { Text("Email") },
        modifier = Modifier.fillMaxWidth(),
        textStyle = MaterialTheme.typography.bodyMedium,
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            unfocusedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
            focusedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,

            )
    )
}

@Composable
fun PasswordTextField(password: MutableState<String>) {
    TextField(
        value = password.value,
        onValueChange = { password.value = it },
        placeholder = { Text("Password") },
        modifier = Modifier.fillMaxWidth(),
        textStyle = MaterialTheme.typography.bodyMedium,
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            unfocusedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
            focusedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
        ),
        visualTransformation = PasswordVisualTransformation()

    )

}

@Composable
fun UsernameTextField(username: MutableState<String>) {
    TextField(
        value = username.value,
        onValueChange = { username.value = it },
        placeholder = { Text("Username") },
        modifier = Modifier.fillMaxWidth(),
        textStyle = MaterialTheme.typography.bodyMedium,
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            unfocusedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
            focusedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
        )

    )
}

@Composable
fun SubmitButton(isLoading: MutableState<Boolean>, onClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        elevation = ButtonDefaults.buttonElevation(AppElevation.Level5),
        onClick = {
            //TODO Create Account, and Navigate onto Main Page
            onClick()

        }) {
        if (!isLoading.value) {
            Text("Submit")
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
fun AlreadyHasAccountText(onClick: () -> Unit) {
    Text(
        "Already has an Account?",
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.labelMedium,
        modifier = Modifier.clickable {
            //TODO Naviagate onto Login
            onClick()
        }
    )

}


@Preview(showBackground = true)
@Composable
fun SignUpPrev() {
    //SignUpScreen()
    //SignUpForm()
}