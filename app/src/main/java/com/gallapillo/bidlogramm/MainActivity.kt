package com.gallapillo.bidlogramm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
import com.gallapillo.bidlogramm.presentation.posts.AddPostScreen
import com.gallapillo.bidlogramm.presentation.posts.PostDetailScreen
import com.gallapillo.bidlogramm.presentation.profile.EditProfileScreen
import com.gallapillo.bidlogramm.presentation.profile.ProfileScreen
import com.gallapillo.bidlogramm.presentation.profile.UserViewModel
import com.gallapillo.bidlogramm.presentation.profile.posts.PostViewModel
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
                                onClick = {
                                      navController.navigate(Screen.AddPostScreen.route)
                                },
                                containerColor = MainColorDark,
                                contentColor = Color.Black
                            ) {
                                Image(Icons.Filled.Add,"", colorFilter = ColorFilter.tint(Color.Black))
                            }
                        }
                    },
                    bottomBar = {
                        if (showBottomNavigationBar) {
                            BottomNavigationBar(navController)
                        }
                    }
                ) { innerPadding ->
                    val authViewModel: AuthenticationViewModel = hiltViewModel()
                    val userViewModel: UserViewModel = hiltViewModel()
                    val postViewModel: PostViewModel = hiltViewModel()
                    Box(modifier = Modifier.padding(innerPadding)) {
                        mainApp(
                            navController = navController,
                            authViewModel = authViewModel,
                            userViewModel = userViewModel,
                            postViewModel = postViewModel
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun mainApp(
    navController: NavHostController,
    authViewModel: AuthenticationViewModel,
    userViewModel: UserViewModel,
    postViewModel: PostViewModel
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
            ProfileScreen(navController = navController, authenticationViewModel = authViewModel)
        }
        composable(route = Screen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
        composable(route = Screen.EditProfileScreen.route) {
            EditProfileScreen(navController = navController, userViewModel = userViewModel, postViewModel = postViewModel)
        }
        composable(route = Screen.PostDetailScreen.route) {
            PostDetailScreen(navController = navController, userViewModel = userViewModel, postViewModel = postViewModel)
        }
        composable(route = Screen.AddPostScreen.route) {
            AddPostScreen(navController = navController, userViewModel = userViewModel, postViewModel = postViewModel)
        }
    }
}