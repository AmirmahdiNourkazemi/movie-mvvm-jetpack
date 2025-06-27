package com.example.movie.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.models.Data
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
private val repository = Repository()
var state by mutableStateOf(ScreenState())
init {
    viewModelScope.launch {
        val response = repository.getMovieList(state.page)
        state = state.copy(
            movie = response.body()!!.data,

        )
    }
}
}

data class ScreenState(
    val movie: List<Data> = emptyList(),
    val page: Int = 1
)