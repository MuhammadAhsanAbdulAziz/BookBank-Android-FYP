package com.example.bookbank.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookbank.models.BookData
import com.example.bookbank.models.BooksRequest
import com.example.bookbank.models.BooksResponse
import com.example.bookbank.models.RequestNewBookRequest
import com.example.bookbank.repository.BookRepository
import com.example.bookbank.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    // Exposing the StateFlow to the UI, not allowing modification outside
    val bookResponse: StateFlow<NetworkResult<Any>> = bookRepository.bookResponse
    val bookFormResponse: StateFlow<NetworkResult<Any>> = bookRepository.bookFormResponse
    val requestBooksResponse: StateFlow<NetworkResult<Any>> = bookRepository.requestBooksResponse
    val returnBooksResponse: StateFlow<NetworkResult<Any>> = bookRepository.returnBooksResponse

    private val _books = MutableStateFlow<List<BookData>>(emptyList())
    val books: StateFlow<List<BookData>> = _books.asStateFlow()

    private val _booksForOrder = MutableStateFlow<List<BookData>>(emptyList())
    val booksForOrder: StateFlow<List<BookData>> = _booksForOrder.asStateFlow()


    fun getAllBooks(category: String) {
        viewModelScope.launch {
            bookRepository.getAllBooks(category)
            if (bookResponse.value is NetworkResult.Success) {
                val result = bookResponse.value.data as BooksResponse
                _books.value = result.data
            }
        }
    }

    fun getRequestBooks() {
        viewModelScope.launch {
            bookRepository.getRequestBooks()
        }
    }

    fun getReturnBooks() {
        viewModelScope.launch {
            bookRepository.getReturnBooks()
        }
    }

    fun insertBookForm(booksRequest: BooksRequest) {
        viewModelScope.launch {
            bookRepository.insertBookForm(booksRequest)
        }
    }

    fun insertRequestNewBook(requestNewBookRequest: RequestNewBookRequest) {
        viewModelScope.launch {
            bookRepository.insertRequestNewBook(requestNewBookRequest)
        }
    }

    fun insertBooksForOrder(book: BookData, context: Context): Boolean {
        // Check if the book already exists in the list based on its unique id
        if (_booksForOrder.value.none { existingBook -> existingBook.title == book.title }) {
            // If the book does not exist, add it to the list
            _booksForOrder.value += book
            return true
        } else {
            Toast.makeText(context, "Book is already in cart", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    fun removeBooksForOrder(book: BookData) {
        _booksForOrder.value = _booksForOrder.value.filterNot { it == book }

    }

}