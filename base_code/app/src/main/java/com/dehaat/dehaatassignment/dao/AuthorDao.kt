package com.dehaat.dehaatassignment.dao

import com.dehaat.dehaatassignment.model.Author
import com.dehaat.dehaatassignment.model.AuthorsData
import com.dehaat.dehaatassignment.model.AuthorsResponseDto

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AuthorDao {

    @get:Query("select * from Author")
    val authorsList: List<Author>

    @Insert
    suspend fun insertAllAuthors(author: Author)
}
