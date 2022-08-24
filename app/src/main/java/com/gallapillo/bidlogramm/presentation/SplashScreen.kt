package com.gallapillo.bidlogramm.presentation

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.gallapillo.bidlogramm.R
import com.gallapillo.bidlogramm.common.Screen
import com.gallapillo.bidlogramm.presentation.authentication.AuthenticationViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    authViewModel: AuthenticationViewModel
) {
    val authValue = authViewModel.isUserAuthenticated

    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(durationMillis = 1500, easing = {
                OvershootInterpolator(2f).getInterpolation(it)
            })
        )
        delay(3000)
        if (authValue) {
            navController.navigate(Screen.FeedsScreen.route) {
                popUpTo(Screen.SplashScreen.route) {
                    inclusive = true
                }
            }
        } else {
            navController.navigate(Screen.LoginScreen.route) {
                popUpTo(Screen.SplashScreen.route) {
                    inclusive = true
                }
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(painter = painterResource(id = R.drawable.peach_icon), contentDescription = "Logo", modifier = Modifier.fillMaxWidth(0.5f))
    }
}