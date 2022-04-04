package com.gallapillo.bidlogramm.common

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object SignUpScreen : Screen("signup_screen")
    object FeedsScreen: Screen("feeds_screen")
}
