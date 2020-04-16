package com.dehaat.dehaatassignment.dao;

import com.dehaat.dehaatassignment.model.Author;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface AuthorDao {

    @Query("select * from Author")
    LiveData<List<Author>> getAuthorsList();

}
