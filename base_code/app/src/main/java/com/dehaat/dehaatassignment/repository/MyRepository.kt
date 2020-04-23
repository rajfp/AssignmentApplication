package com.dehaat.dehaatassignment.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.dehaat.dehaatassignment.dao.AuthorDao
import com.dehaat.dehaatassignment.dao.BookDao
import com.dehaat.dehaatassignment.database.AppDatabase
import com.dehaat.dehaatassignment.model.Author
import com.dehaat.dehaatassignment.model.AuthorsResponseDto
import com.dehaat.dehaatassignment.model.Book
import com.dehaat.dehaatassignment.rest.AppRestClient
import com.dehaat.dehaatassignment.rest.AppRestClientService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class MyRepository(private val application: Application) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var appRestClientService:AppRestClientService
    private var authorData:MutableLiveData<AuthorsResponseDto>
    private var authorDao:AuthorDao?=null
    private var booksDao:BookDao?=null

    init {
        val appDatabase=AppDatabase.getDatabase(application)
        appRestClientService= AppRestClient.getInstance().create(AppRestClientService::class.java)
        authorData= MutableLiveData()
        authorDao=appDatabase?.authorDao()
        booksDao=appDatabase?.bookDao()
    }

    fun  getAuthorList():MutableLiveData<AuthorsResponseDto>{
        var response=appRestClientService.fetchListOfAuthors()
        response.enqueue(object : Callback<AuthorsResponseDto> {
            override fun onFailure(call: Call<AuthorsResponseDto>, t: Throwable) {
                authorData.value=null
            }

            override fun onResponse(call: Call<AuthorsResponseDto>, response: Response<AuthorsResponseDto>) {
                authorData.value=response.body()
            }

        })
        return authorData
    }

    fun saveAuthorInfo(authorsResponseDto: AuthorsResponseDto?){
        launch {
            for (i in authorsResponseDto?.data!!) {
                authorDao?.insertAllAuthors(Author(i.author_name, i.author_bio))
                booksDao?.insertAll(Book(i.author_name, i.books))            }
        }
    }

     fun fetchBooks(authorName:String?)=booksDao?.getBooks(authorName)

}