package com.example.flightsapp.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.flightsapp.Authentication.view.LoginScreen
import com.example.flightsapp.Authentication.view.SignUpScreen
import com.example.flightsapp.Authentication.viewmodel.AuthViewModel
import com.example.flightsapp.MainScreen.view.MainPage
import com.example.flightsapp.MainScreen.viewmodel.mainPageViewModel

@Composable
fun NavigationGrath(
    //authViewModel: AuthViewModel,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screens.LoginScreen.route)
    {
        val authViewModel : AuthViewModel = AuthViewModel()
        val mainPageViewModel : mainPageViewModel = mainPageViewModel()

        composable(Screens.LoginScreen.route) {
            LoginScreen(
                { navController.navigate(Screens.SignUpScreen.route) },
                { navController.navigate(Screens.HomeScreen.route) },
                viewModel = authViewModel
            )

        }
        composable(Screens.SignUpScreen.route) {
            SignUpScreen(
                viewModel = authViewModel,
                navOnSignUpSucess = {navController.navigate(Screens.HomeScreen.route)} ,
                navToLogin = {navController.navigate(Screens.LoginScreen.route)}
            )
        }
        composable(Screens.HomeScreen.route) {
            MainPage(
                viewModel = mainPageViewModel,
            )
        }
    }
}