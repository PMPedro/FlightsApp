package com.example.flightsapp.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.flightsapp.Authentication.view.LoginScreen
import com.example.flightsapp.Authentication.view.SignUpScreen
import com.example.flightsapp.Authentication.viewmodel.AuthViewModel

@Composable
fun NavigationGrath(
    //authViewModel: AuthViewModel,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screens.LoginScreen.route)
    {
        val authViewModel : AuthViewModel = AuthViewModel()

        composable(Screens.LoginScreen.route) {
            LoginScreen(
                { navController.navigate(Screens.SignUpScreen.route) },
                { navController.navigate(Screens.SignUpScreen.route) },  //TODO change to homeScreen, after its done
                viewModel = authViewModel
            )

        }
        composable(Screens.SignUpScreen.route) {
            SignUpScreen(
                viewModel = authViewModel,
                navOnSignUpSucess = {navController.navigate(Screens.SignUpScreen.route)} , //TODO change to homescreen, after its done
                navToLogin = {navController.navigate(Screens.LoginScreen.route)}
            )
        }
    }
}