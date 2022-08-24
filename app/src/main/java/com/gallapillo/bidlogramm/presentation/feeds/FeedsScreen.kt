package com.gallapillo.bidlogramm.presentation.feeds

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gallapillo.bidlogramm.common.Screen

@Composable
fun FeedsScreen(
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row (
            modifier = Modifier
                .padding(top = 12.dp, bottom = 6.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "BidloGramm", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }
        LazyColumn(
            content = {
                items(5) {
                    PostCard(navController,
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.PostDetailScreen.route)
                        }
                    )
                }
            }
        )
    }
}