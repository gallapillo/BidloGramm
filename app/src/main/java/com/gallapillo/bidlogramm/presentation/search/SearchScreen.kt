package com.gallapillo.bidlogramm.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.gallapillo.bidlogramm.presentation.BottomNavigationItem
import com.gallapillo.bidlogramm.presentation.BottomNavigationMenu

@Composable
fun SearchScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column (modifier = Modifier.weight(1f)) {
            Text(text = "Search COMING SOOn")
        }
        BottomNavigationMenu(selectedItem = BottomNavigationItem.SEARCH,
            navController = navController)
    }
}