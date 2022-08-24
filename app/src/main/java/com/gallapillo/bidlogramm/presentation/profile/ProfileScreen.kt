package com.gallapillo.bidlogramm.presentation.profile

import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.gallapillo.bidlogramm.R
import com.gallapillo.bidlogramm.common.Response
import com.gallapillo.bidlogramm.common.Screen
import com.gallapillo.bidlogramm.domain.model.TabModel
import com.gallapillo.bidlogramm.presentation.BottomNavigationItem
import com.gallapillo.bidlogramm.presentation.BottomNavigationMenu
import com.gallapillo.bidlogramm.presentation.Toast
import com.gallapillo.bidlogramm.presentation.authentication.AuthenticationViewModel
import com.gallapillo.bidlogramm.presentation.profile.components.*

@ExperimentalFoundationApi
@Composable
fun ProfileScreen(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel
) {
    val userViewModel : UserViewModel = hiltViewModel()
    userViewModel.getUserInfo()

    val context = LocalContext.current

    when (val response = userViewModel.getUserData.value) {
        is Response.Loading -> {
            CircularProgressIndicator()
        }
        is Response.Success -> {
            if (response.data != null) {
                val user = response.data
                var selectedTabIndex by remember { mutableStateOf(0) }

                Column (
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row(modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth()
                        .padding(top = 16.dp), verticalAlignment = Alignment.CenterVertically){
                        Spacer(
                            Modifier
                                .weight(1f)
                                .fillMaxHeight())
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit button",
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(Screen.EditProfileScreen.route)
                                }
                                .padding(horizontal = 12.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Sign out button",
                            modifier = Modifier
                                .clickable {
                                    authenticationViewModel.signOut()
                                    when(val response = authenticationViewModel.signOutState.value) {
                                        is Response.Loading -> {

                                        }
                                        is Response.Error -> {
                                            makeText(
                                                context,
                                                response.message,
                                                LENGTH_LONG
                                            ).show()
                                        }
                                        is Response.Success -> {
                                            if (response.data) {
                                                navController.navigate(Screen.LoginScreen.route) {
                                                    popUpTo(Screen.ProfileScreen.route) {
                                                        inclusive = true
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                .padding(end = 12.dp)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 10.dp,
                                start = 10.dp,
                                bottom = 10.dp,
                                end = 20.dp
                            )

                    ) {
                        RoundedImage(
                            image = rememberImagePainter(data = user.imageURl),
                            modifier = Modifier
                                .size(100.dp)
                                .weight(3.5f)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = user.userName)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ProfileStats(
                            numberText = user.totalPosts,
                            text = "Posts",
                            navController = navController
                        )
                        ProfileStats(
                            numberText = user.followers.size.toString(),
                            text = "Followers",
                            navController = navController
                        )
                        ProfileStats(
                            numberText = user.following.size.toString(),
                            text = "Following",
                            navController = navController
                        )
                    }
                Spacer(modifier = Modifier.height(15.dp))
                    TabView(
                        tabModels = listOf(
                            TabModel(
                                image = painterResource(id = R.drawable.ic_launcher_background),
                                text = "Posts"
                            ),
                            TabModel(
                                image = painterResource(id = R.drawable.ic_launcher_background),
                                text = "Clips"
                            ),
                            TabModel(
                                image = painterResource(id = R.drawable.ic_baseline_edit_24),
                                text = "Igtv"
                            )
                        )
                    ) {
                        selectedTabIndex = it
                    }
                    when (selectedTabIndex) {
                        0 -> {
                            PostsSection(
                                posts = listOf(
                                    painterResource(id = R.drawable.ic_launcher_background),
                                    painterResource(id = R.drawable.ic_launcher_background),
                                    painterResource(id = R.drawable.ic_launcher_background),
                                    painterResource(id = R.drawable.ic_launcher_background),
                                    painterResource(id = R.drawable.ic_launcher_background),
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)
                            )
                        }
                        1 -> {

                        }
                        2 -> {

                        }
                    }
                }
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

            }
        }
    }
}