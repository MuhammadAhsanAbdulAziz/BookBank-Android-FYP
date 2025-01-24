package com.example.bookbank.api

import com.example.bookbank.models.BooksRequest
import com.example.bookbank.models.BooksResponse
import com.example.bookbank.models.LoginRequest
import com.example.bookbank.models.RequestBooksResponse
import com.example.bookbank.models.RequestNewBookRequest
import com.example.bookbank.models.ReturnBooksResponse
import com.example.bookbank.models.SuccessResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BookApi {

    @GET("database/books/select")
    suspend fun getAllBooks(
        @Query("booksCategory") booksCategory: String
    ): Response<BooksResponse>

    @POST("database/forms/insert")
    suspend fun insertBookForm(@Body booksRequest: BooksRequest) : Response<SuccessResponse>

    @GET("mobile/form/select")
    suspend fun getRequestBooks() : Response<RequestBooksResponse>

    @GET("mobile/form/select/borrowedStatus")
    suspend fun getReturnedBooks() : Response<ReturnBooksResponse>

    @POST("database/requestBooks/insert")
    suspend fun insertRequestNewBook(@Body requestNewBookRequest: RequestNewBookRequest): Response<SuccessResponse>

}