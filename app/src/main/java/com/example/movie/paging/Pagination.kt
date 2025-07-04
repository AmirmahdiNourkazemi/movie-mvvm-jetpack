package com.example.movie.paging

interface Pagination<Key,Item> {
    suspend fun loadNextPage()
    fun reset()
}