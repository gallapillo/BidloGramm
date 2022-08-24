package com.gallapillo.bidlogramm.presentation.posts

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
import com.gallapillo.bidlogramm.presentation.profile.UserViewModel
import com.gallapillo.bidlogramm.presentation.profile.posts.PostViewModel

@Composable
fun AddPostScreen(
    navController: NavHostController,
    userViewModel: UserViewModel,
    postViewModel: PostViewModel
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.padding(start = 16.dp, top = 12.dp, end = 24.dp)) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                modifier = Modifier.clickable {
                    navController.navigate(Screen.FeedsScreen.route) {
                        popUpTo(Screen.AddPostScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
            Text(text = "Create post", modifier = Modifier.padding(start = 24.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
    }

}