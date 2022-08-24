package com.gallapillo.bidlogramm.presentation.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gallapillo.bidlogramm.common.Screen
import com.gallapillo.bidlogramm.presentation.profile.posts.PostViewModel

@Composable
fun EditProfileScreen(
    navController: NavHostController,
    userViewModel: UserViewModel,
    postViewModel: PostViewModel
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.padding(start = 16.dp, top = 12.dp)) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                modifier = Modifier.clickable {
                    navController.navigate(Screen.ProfileScreen.route) {
                        popUpTo(Screen.EditProfileScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
            Text(text = "Edit profile", modifier = Modifier.padding(start = 24.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}