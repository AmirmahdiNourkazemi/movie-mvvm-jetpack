package com.example.movie.navigation

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movie.R
import com.example.movie.models.Data
import com.example.movie.viewModel.MovieViewModel

@Composable
fun HomeScreen(navController: NavHostController, modifier: Modifier) {
    val movieViewModel = viewModel<MovieViewModel>()
    val state = movieViewModel.state
    Scaffold(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            TopBar()
        }, content = { paddingValues ->
            LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier.padding(paddingValues)) {
                items(state.movie.size) {
                    if (it >= state.movie.size - 1 && !state.endReach && !state.isLoading) {
                        movieViewModel.loadNextItems()
                    }
                    ItemUI(itemIndex = it, movieList = state.movie, navController = navController)
                }
                item(state.isLoading) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        CircularProgressIndicator(color = ProgressIndicatorDefaults.circularColor)
                    }
                    if (!state.error.isNullOrEmpty()) {
                        Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemUI(itemIndex: Int, movieList: List<Data>, navController: NavHostController) {
    Card(
        Modifier
            .wrapContentSize()
            .padding(10.dp)
            .clickable {
                navController.navigate("Details Screen/${movieList[itemIndex].id}")
            }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter,
        ) {

            AsyncImage(
                model = movieList[itemIndex].poster,
                contentDescription = movieList[itemIndex].title,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp))
            )
            Box(
                modifier = Modifier
                    .offset(x = 8.dp, y = 8.dp) // distance from left and top
                    .background(
                        color = Color.White.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(horizontal = 6.dp, vertical = 4.dp)
                    .align(Alignment.TopStart)

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        tint = Color.Yellow,
                        contentDescription = "IMDb Rating",
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = movieList[itemIndex].imdb_rating,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat))
                        )
                    )
                }
            }
            Column(
                modifier = Modifier
                    .background(color = Color.White.copy(alpha = 0.7f))
                    .fillMaxHeight()
                    .fillMaxSize()
                    .padding(vertical = 8.dp, horizontal = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    movieList[itemIndex].title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .basicMarquee(),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                    )
                )

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(
                alpha = 0.4f
            )
        ),
        title = {
            Text(
                text = "Movies", style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )
        }

    )
}
