package com.gallapillo.bidlogramm.presentation.profile

import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.gallapillo.bidlogramm.domain.model.TabModel
import com.gallapillo.bidlogramm.presentation.BottomNavigationItem
import com.gallapillo.bidlogramm.presentation.BottomNavigationMenu
import com.gallapillo.bidlogramm.presentation.Toast
import com.gallapillo.bidlogramm.presentation.profile.components.*

@ExperimentalFoundationApi
@Composable
fun ProfileScreen(
    navController: NavController
) {
    val userViewModel : UserViewModel = hiltViewModel()
    userViewModel.getUserInfo()

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
                    Spacer(modifier = Modifier.padding(top = 12.dp))
                    Row(horizontalArrangement = Arrangement.End) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit button",
                            modifier = Modifier.clickable {

                            }.padding(start = 12.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Sign out button",
                            modifier = Modifier.clickable {

                            }.padding(end = 12.dp)
                        )
                    }
                    /*Column(modifier = Modifier.weight(1f)) {
                        TopAppBar(
                            title = {
                                Text(
                                    text = user.name,
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = FontStyle.Italic,
                                    fontSize = 24.sp
                                )
                            },
                            actions = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_edit_24),
                                    contentDescription = "create",
                                    tint = Color.Black,
                                    modifier = Modifier.size(30.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_edit_24),
                                    contentDescription = "More options",
                                    tint = Color.Black,
                                    modifier = Modifier.size(30.dp)
                                )
                            },
                            backgroundColor = Color.White,
                            elevation = 10.dp
                        )
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
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
                                Spacer(modifier = Modifier.width(10.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceAround,
                                    modifier = Modifier.weight(6.5f)
                                ) {
                                    ProfileStats(
                                        numberText = "133",
                                        text = "Posts",
                                        navController = navController
                                    )
                                    ProfileStats(
                                        numberText = "133",
                                        text = "Followers",
                                        navController = navController
                                    )
                                    ProfileStats(
                                        numberText = user.following.size.toString(),
                                        text = "Following",
                                        navController = navController
                                    )
                                }
                            }
                            MyProfile(
                                displayName = user.name,
                                bio = user.bio,
                                url = user.url,
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.padding(horizontal = 20.dp)
                            ) {
                                ActionButton(
                                    text = "Edit Profile",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(0.45f)
                                        .height(35.dp)
                                        .clickable {

                                        }

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
                    }*/

                    
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