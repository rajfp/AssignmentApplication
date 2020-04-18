package com.dehaat.dehaatassignment.dao;

import com.dehaat.dehaatassignment.model.Book;
import com.dehaat.dehaatassignment.model.books;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface BookDao {

    @Insert
    void insertAll(Book book);

    @Query("select * from Book where author_name = :authorName")
    List<Book> getBooks(String authorName);


}
