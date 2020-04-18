package com.dehaat.dehaatassignment.dao;

import com.dehaat.dehaatassignment.model.Author;
import com.dehaat.dehaatassignment.model.AuthorsData;
import com.dehaat.dehaatassignment.model.AuthorsResponseDto;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface AuthorDao {

    @Query("select * from Author")
    List<Author> getAuthorsList();

    @Insert
    void insertAllAuthors(Author author);



}
