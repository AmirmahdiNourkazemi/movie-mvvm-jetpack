package com.example.movie.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.movie.R

@Composable
fun BannerScreen(navController: NavController, modifier: Modifier) {
  Box(modifier = modifier.fillMaxSize()){
      Image(
          painter = painterResource(id = R.drawable.banner),
          contentDescription = "",
          modifier = modifier.fillMaxSize(), contentScale = ContentScale.Crop
      )
      Column(modifier.wrapContentHeight().fillMaxWidth().align(
          alignment = Alignment.BottomEnd
      ).background(color = Color.White.copy(0.4f))) {
            Text(text = "salam")
      }
  }
}