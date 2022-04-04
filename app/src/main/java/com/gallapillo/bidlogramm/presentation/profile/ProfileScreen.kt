package com.gallapillo.bidlogramm.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.gallapillo.bidlogramm.presentation.BottomNavigationItem
import com.gallapillo.bidlogramm.presentation.BottomNavigationMenu

@Composable
fun ProfileScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column (modifier = Modifier.weight(1f)) {
            Text(text = "PROFILE COMING SOOn")
        }
        BottomNavigationMenu(selectedItem = BottomNavigationItem.PROFILE,
            navController = navController)
    }
}