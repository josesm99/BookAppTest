package com.example.bookapptest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapptest.model.BookResponse
import com.example.bookapptest.repository.BookRepository
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {

    val myResponse: MutableLiveData<ResponseState<BookResponse>> = MutableLiveData()

    fun getBooks(query: String) {
        myResponse.postValue(ResponseState.OnLoading())
        viewModelScope.launch {
            val response = repository.getBooks(query)
            if (response.isSuccessful) {
                myResponse.postValue(ResponseState.OnSuccess(response.body()!!))
            } else {
                myResponse.postValue(ResponseState.OnError(response.message()))
            }
        }
    }
}

sealed class ResponseState<T> {
    data class OnSuccess<T>(val value: T) : ResponseState<T>()
    data class OnError<T>(val message: String) : ResponseState<T>()
    class OnLoading<T> : ResponseState<T>()
}