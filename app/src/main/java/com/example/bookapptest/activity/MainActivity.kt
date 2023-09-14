package com.example.bookapptest.activity

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookapptest.databinding.ActivityMainBinding
import com.example.bookapptest.livedatamodel.observe
import com.example.bookapptest.model.BookResponse
import com.example.bookapptest.viewmodel.BookViewModel
import com.example.bookapptest.viewmodel.ResponseState
import org.koin.androidx.viewmodel.ext.android.viewModel

const val SHOW_CONTENT_STATE = 0
const val SHOW_LOADER = 1
const val SHOW_ERROR_STATE = 2

class MainActivity : AppCompatActivity() {

    private val bookViewModel: BookViewModel by viewModel()

    private val bookAdapter = BookAdapter()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservables()
        initViews()
    }

    private fun initViews() {
        initSearchView()
        initRecycler()
    }

    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    bookViewModel.getBooks(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = bookAdapter
    }

    private fun initObservables() {
        with(bookViewModel) {
            observe(myResponse, ::onGetBooksResponse)
        }
    }

    private fun onGetBooksResponse(result: ResponseState<BookResponse>?) {
        when (result) {
            is ResponseState.OnLoading -> binding.viewFlipper.displayedChild = SHOW_LOADER
            is ResponseState.OnSuccess -> handleOnGetBooksResponseSuccess(result.value)
            is ResponseState.OnError -> handleOnGetBooksResponseError()
            else -> {}
        }
    }

    private fun handleOnGetBooksResponseError() {
        binding.viewFlipper.displayedChild = SHOW_ERROR_STATE
    }

    private fun handleOnGetBooksResponseSuccess(bookResponse: BookResponse) {

        bookAdapter.updateList(bookResponse.items)

        binding.viewFlipper.displayedChild = SHOW_CONTENT_STATE
    }
}