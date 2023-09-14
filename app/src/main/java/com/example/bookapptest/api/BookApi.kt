package com.example.bookapptest.api

import com.example.bookapptest.model.BookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {
    @GET(" ")
    suspend fun searchBooks(@Query("q") query: String): Response<BookResponse>
}