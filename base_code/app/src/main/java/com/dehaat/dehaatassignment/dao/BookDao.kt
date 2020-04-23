package com.dehaat.dehaatassignment.dao

import afu.org.checkerframework.checker.igj.qual.Mutable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dehaat.dehaatassignment.model.Book
import com.dehaat.dehaatassignment.model.books

import java.util.ArrayList

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {

    @Insert
    suspend fun insertAll(book: Book)

    @Query("select * from Book where author_name = :authorName")
     fun getBooks(authorName: String?): LiveData<Book>


}
