package com.gallapillo.bidlogramm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gallapillo.bidlogramm.common.Screen
import com.gallapillo.bidlogramm.presentation.authentication.AuthenticationViewModel
import com.gallapillo.bidlogramm.presentation.authentication.LoginScreen
import com.gallapillo.bidlogramm.presentation.authentication.SignUpScreen
import com.gallapillo.bidlogramm.presentation.SplashScreen
import com.gallapillo.bidlogramm.presentation.bottomnavigation.BottomNavigationBar
import com.gallapillo.bidlogramm.presentation.feeds.FeedsScreen
import com.gallapillo.bidlogramm.presentation.profile.ProfileScreen
import com.gallapillo.bidlogramm.presentation.search.SearchScreen


import com.gallapillo.bidlogramm.presentation.theme.BidloGrammTheme
import com.gallapillo.bidlogramm.presentation.theme.MainColor
import com.gallapillo.bidlogramm.presentation.theme.MainColorDark
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BidloGrammTheme {

                var showFloatActionButton by rememberSaveable { mutableStateOf(true) }
                var showBottomNavigationBar by rememberSaveable { mutableStateOf(true) }

                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                showBottomNavigationBar = when (navBackStackEntry?.destination?.route) {
                    Screen.ProfileScreen.route -> true
                    Screen.SearchScreen.route -> true
                    Screen.FeedsScreen.route -> true
                    else -> false
                }

                showFloatActionButton = when (navBackStackEntry?.destination?.route) {
                    Screen.FeedsScreen.route -> true
                    else -> false
                }

                Scaffold(
                    backgroundColor = MaterialTheme.colors.background,
                    floatingActionButton = {
                        if (showFloatActionButton) {
                            FloatingActionButton(
                                onClick = {  },
                                containerColor = MainColorDark
                            ) {
                                Icon(Icons.Filled.Add,"")
                            }
                        }
                    },
                    bottomBar = {
                        if (showBottomNavigationBar) {
                            BottomNavigationBar(navController)
                        }
                    }
                ) {
                    val authViewModel: AuthenticationViewModel = hiltViewModel()
                    mainApp(navController = navController, authViewModel = authViewModel)
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun mainApp(
    navController: NavHostController,
    authViewModel: AuthenticationViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController, viewModel = authViewModel)
        }
        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen(navController = navController, viewModel = authViewModel)
        }
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController, authViewModel = authViewModel)
        }
        composable(route = Screen.FeedsScreen.route) {
            FeedsScreen(navController = navController)
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
        composable(route = Screen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
    }
}