package com.gallapillo.bidlogramm.presentation.feeds

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gallapillo.bidlogramm.R
import com.gallapillo.bidlogramm.common.Screen
import com.gallapillo.bidlogramm.presentation.profile.RoundedImage

@Composable
fun PostCard(
    navController: NavController,
    modifier: Modifier = Modifier
) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.padding(horizontal = 12.dp),
        ) {
            RoundedImage(
                image = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier.size(48.dp).padding(horizontal = 12.dp)
            )
            Column(
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Text("Alfredo Macani")
                Text("data")
            }
        }
        Spacer(Modifier.size(16.dp))
        Card(elevation = 4.dp, modifier = modifier) {
            Image(painter = painterResource(id = R.drawable.test_image), contentDescription = "")
        }
        Spacer(Modifier.size(16.dp))
}