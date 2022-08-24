package com.gallapillo.bidlogramm.common

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object SignUpScreen : Screen("signup_screen")
    object FeedsScreen : Screen("feeds_screen")
    object ProfileScreen : Screen("profile_Screen")
    object SearchScreen : Screen("search_screen")
    object AddPostScreen : Screen("add_post_screen")
    object PostDetailScreen : Screen("post_detail_screen")
    object EditProfileScreen : Screen("edit_profile_screen")
}
