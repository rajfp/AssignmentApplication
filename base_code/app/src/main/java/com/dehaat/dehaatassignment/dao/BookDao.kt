package com.dehaat.dehaatassignment.dao

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
    suspend fun getBooks(authorName: String): Book


}
