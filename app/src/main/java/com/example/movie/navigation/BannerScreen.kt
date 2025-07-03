package com.example.movie.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movie.R

@Composable
fun BannerScreen(navController: NavController, modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(WindowInsets.navigationBars.asPaddingValues())
    ) {
        Image(
            painter = painterResource(id = R.drawable.banner),
            contentDescription = "",
            modifier = modifier.fillMaxSize(), contentScale = ContentScale.Crop
        )
        Column(
            modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(
                    alignment = Alignment.BottomEnd
                )
                .background(
                    color = Color.White.copy(0.4f),
                    RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)
                )
        ) {
            Column(
                modifier = modifier.padding(horizontal = 10.dp, vertical = 20.dp)
            ) {
                Text(
                    text = "🌍 Enjoy the World of movie",
                    modifier = modifier.padding(bottom = 10.dp),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = "👌 Here you can find every thing", style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        fontSize = 14.sp,
                    )
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = "😍 See in all platforms", style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        fontSize = 14.sp,
                    )
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = "😍 Best quality movies", style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        fontSize = 14.sp,
                    )
                )
                Spacer(modifier = modifier.height(20.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(Color.White.copy(0.4f)),
                    shape = RoundedCornerShape(10.dp),
                    modifier = modifier
                        .fillMaxWidth(),
                    onClick = { navController.navigate("Home Screen") },
                ) {
                    Text("Start",style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        fontSize = 14.sp,
                    ))
                }
            }
        }
    }
}

