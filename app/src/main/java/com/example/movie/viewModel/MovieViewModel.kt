package com.example.movie.viewModel

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.models.Data
import com.example.movie.models.Details
import com.example.movie.paging.PaginationFactory
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val repository = Repository()
    var state by mutableStateOf(ScreenState())
    var id by mutableIntStateOf(0)

    private val pagination = PaginationFactory(
        initialPage = state.page,
        onLoadUpdated = {
            state = state.copy(
                isLoading = it
            )
        },
        onRequest = { nextPage -> repository.getMovieList(nextPage) },
        getNextKey = {
            state.page + 1
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newPage ->
            state = state.copy(
                movie = state.movie + items.data,
                page = newPage,
                endReach = state.page == 25
            )
        }
    )

    init {
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            pagination.loadNextPage()
        }
    }

    //init {
//    viewModelScope.launch {
//        val response = repository.getMovieList(state.page)
//        state = state.copy(
//            movie = response.body()!!.data,
//
//        )
//    }
//}
    fun getDetailsById() {
        viewModelScope.launch {
            try {
                val response = repository.getMovieById(id)
                if (response.isSuccessful) {
                    state = state.copy(
                        detail = response.body()!!
                    )
                }
            } catch (e: Exception) {

            }
        }
    }
}

data class ScreenState(
    val movie: List<Data> = emptyList(),
    val page: Int = 1,
    val detail: Details = Details(),
    val endReach: Boolean = false,
    val error: String? = null,
    val isLoading: Boolean = false,
)