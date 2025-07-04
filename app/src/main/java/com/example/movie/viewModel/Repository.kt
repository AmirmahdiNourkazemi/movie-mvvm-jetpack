package com.example.movie.viewModel

import com.example.movie.models.Details
import com.example.movie.models.MoviesList
import com.example.movie.utils.RetrofitInstance
import retrofit2.Response

class Repository {
    suspend fun getMovieList(page:Int): Response<MoviesList>{
        return RetrofitInstance.api.getMovies(page)
    }

    suspend fun getMovieById(id:Int): Response<Details>{
      return RetrofitInstance.api.getDetailById(id)
    }
}