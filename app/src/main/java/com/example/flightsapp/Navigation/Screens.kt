package com.example.flightsapp.Navigation

sealed class Screens(val route: String){

    object LoginScreen: Screens("loginscreen")
    object SignUpScreen: Screens("signupscreen")

    object HomeScreen: Screens("homescreen")


}