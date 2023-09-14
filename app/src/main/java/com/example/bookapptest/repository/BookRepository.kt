package com.example.bookapptest.repository

import com.example.bookapptest.api.BookApi
import com.example.bookapptest.model.BookResponse
import retrofit2.Response

class BookRepository(private val bookApi: BookApi) {

    suspend fun getBooks(query: String): Response<BookResponse> {
        return bookApi.searchBooks(query)
    }
}