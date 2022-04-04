package com.gallapillo.bidlogramm.presentation

import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun Toast(
    message: String
) {
    makeText(LocalContext.current, message, LENGTH_LONG).show()
}