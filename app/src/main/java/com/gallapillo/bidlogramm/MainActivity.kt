package com.gallapillo.bidlogramm

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gallapillo.bidlogramm.common.Screen
import com.gallapillo.bidlogramm.presentation.Authentication.AuthenticationViewModel
import com.gallapillo.bidlogramm.presentation.Authentication.LoginScreen
import com.gallapillo.bidlogramm.presentation.Authentication.SignUpScreen
import com.gallapillo.bidlogramm.presentation.SplashScreen
import com.gallapillo.bidlogramm.presentation.feeds.FeedsScreen


import com.gallapillo.bidlogramm.ui.theme.BidloGrammTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BidloGrammTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    val authViewModel: AuthenticationViewModel = hiltViewModel()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SplashScreen.route
                    ) {
                        composable(route = Screen.LoginScreen.route) {
                            LoginScreen()
                        }
                        composable(route = Screen.SignUpScreen.route) {
                            SignUpScreen()
                        }
                        composable(route = Screen.SplashScreen.route) {
                            SplashScreen(navController = navController, authViewModel = authViewModel)
                        }
                        composable(route = Screen.FeedsScreen.route) {
                            FeedsScreen()
                        }
                    }
                }
            }
        }
    }
}
