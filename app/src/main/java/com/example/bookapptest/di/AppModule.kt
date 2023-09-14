package com.example.bookapptest.di

import com.example.bookapptest.api.BookApi
import com.example.bookapptest.repository.BookRepository
import com.example.bookapptest.viewmodel.BookViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging);

        val baseUrl = "https://www.googleapis.com/books/v1/volumes/"
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    factory {
        get<Retrofit>().create(BookApi::class.java)
    }

    factory { BookRepository(get()) }

    viewModel { BookViewModel(get()) }
}