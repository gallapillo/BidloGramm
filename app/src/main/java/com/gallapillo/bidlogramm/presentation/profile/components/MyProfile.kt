package com.gallapillo.bidlogramm.presentation.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyProfile(
    displayName: String,
    bio: String,
    url: String,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text (
            text = displayName,
            fontWeight = FontWeight.Bold,
            lineHeight = 20.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text (
            text = bio,
            fontWeight = FontWeight.Bold,
            lineHeight = 20.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text (
            text = url,
            fontWeight = FontWeight.Bold,
            lineHeight = 20.sp
        )
    }
}