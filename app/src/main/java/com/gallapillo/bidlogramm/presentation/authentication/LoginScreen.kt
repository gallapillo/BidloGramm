package com.gallapillo.bidlogramm.presentation.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gallapillo.bidlogramm.R
import com.gallapillo.bidlogramm.common.Response
import com.gallapillo.bidlogramm.common.Screen
import com.gallapillo.bidlogramm.presentation.Toast
import com.gallapillo.bidlogramm.presentation.theme.MainColor

@Composable
fun LoginScreen(
    navController : NavHostController,
    viewModel: AuthenticationViewModel
) {
    Box(modifier= Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val emailState = remember {
                mutableStateOf("")
            }
            val passwordState = remember {
                mutableStateOf("")
            }
            Image(
                painter = painterResource(id = R.drawable.peach_icon),
                contentDescription = "Logo",
                modifier = Modifier.padding(top = 16.dp).fillMaxWidth(0.5f)
            )
            Text(
                text = "Sign In",
                modifier = Modifier.padding(10.dp),
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif
            )
            OutlinedTextField(
                value = emailState.value,
                onValueChange = {
                    emailState.value = it
                },
                modifier = Modifier.padding(10.dp),
                label = {
                    Text(text = "Email")
                }
            )
            OutlinedTextField(
                value = passwordState.value,
                onValueChange = {
                    passwordState.value = it
                },
                modifier = Modifier.padding(10.dp),
                label = {
                    Text(text = "Password")
                },
                visualTransformation = PasswordVisualTransformation()
            )
            androidx.compose.material3.Button(
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = MainColor),
                onClick = {
                    viewModel.signIn(
                        email = emailState.value,
                        password = passwordState.value
                    )
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "Sign In"
                )
                when (val response = viewModel.signInState.value) {
                    is Response.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    is Response.Error -> {
                        Toast(message = response.message)
                    }
                    is Response.Success -> {
                        if (response.data) {
                            navController.navigate(Screen.FeedsScreen.route) {
                                popUpTo(Screen.LoginScreen.route) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            }
            Text(
                text="New User? Sing Up",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navController.navigate(Screen.SignUpScreen.route) {
                            launchSingleTop = true
                        }
                    }
            )
        }
    }
}