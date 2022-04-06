package com.gallapillo.bidlogramm.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gallapillo.bidlogramm.common.Response
import com.gallapillo.bidlogramm.presentation.BottomNavigationItem
import com.gallapillo.bidlogramm.presentation.BottomNavigationMenu
import com.gallapillo.bidlogramm.presentation.Toast

@Composable
fun ProfileScreen(
    navController: NavController
) {
    val userViewModel : UserViewModel = hiltViewModel()
    userViewModel.getUserInfo()

    when (val response = userViewModel.getUserData.value) {
        is Response.Loading -> {
            
        }
        is Response.Success -> {
            Toast(message = "Successfull ${response.data.toString()}")
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Column (modifier = Modifier.weight(1f)) {
                    response.data?.name?.let { Text(text = it) }
                }
                BottomNavigationMenu(selectedItem = BottomNavigationItem.PROFILE,
                    navController = navController)
            }
        }
        is Response.Error -> {
            Toast(message = "ERROR WITH RESPONSE")
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Column (modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "ERRPR WITH PROFILE")
                }
                BottomNavigationMenu(selectedItem = BottomNavigationItem.PROFILE,
                    navController = navController)
            }
        }
    } 


}