package com.example.bookbank.repository

import com.example.bookbank.api.BookApi
import com.example.bookbank.models.BooksRequest
import com.example.bookbank.models.LoginRequest
import com.example.bookbank.models.RequestNewBookRequest
import com.example.bookbank.util.Helper.handleResponse
import com.example.bookbank.util.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class BookRepository @Inject constructor(private val bookApi: BookApi) {

    private val _bookResponse = MutableStateFlow<NetworkResult<Any>>(NetworkResult.Idle())
    val bookResponse: StateFlow<NetworkResult<Any>> = _bookResponse.asStateFlow()

    private val _bookFormResponse = MutableStateFlow<NetworkResult<Any>>(NetworkResult.Idle())
    val bookFormResponse: StateFlow<NetworkResult<Any>> = _bookFormResponse.asStateFlow()

    private val _requestBooksResponse = MutableStateFlow<NetworkResult<Any>>(NetworkResult.Idle())
    val requestBooksResponse: StateFlow<NetworkResult<Any>> = _requestBooksResponse.asStateFlow()

    private val _returnBooksResponse = MutableStateFlow<NetworkResult<Any>>(NetworkResult.Idle())
    val returnBooksResponse: StateFlow<NetworkResult<Any>> = _returnBooksResponse.asStateFlow()

    suspend fun getAllBooks(category:String) {
        _bookResponse.value = NetworkResult.Loading()
        val response = bookApi.getAllBooks(category)
        handleResponse(response, _bookResponse)

    }

    suspend fun getRequestBooks() {
        _requestBooksResponse.value = NetworkResult.Loading()
        val response = bookApi.getRequestBooks()
        handleResponse(response, _requestBooksResponse)

    }

    suspend fun getReturnBooks() {
        _returnBooksResponse.value = NetworkResult.Loading()
        val response = bookApi.getReturnedBooks()
        handleResponse(response, _returnBooksResponse)

    }

    suspend fun insertBookForm(booksRequest: BooksRequest) {
        _bookFormResponse.value = NetworkResult.Loading()
        val response = bookApi.insertBookForm(booksRequest)
        handleResponse(response,_bookFormResponse)

    }

    suspend fun insertRequestNewBook(requestNewBookRequest: RequestNewBookRequest) {
        _bookFormResponse.value = NetworkResult.Loading()
        val response = bookApi.insertRequestNewBook(requestNewBookRequest)
        handleResponse(response,_bookFormResponse)

    }
}
