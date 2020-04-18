package com.dehaat.dehaatassignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dehaat.dehaatassignment.Converter.Converters

import com.dehaat.dehaatassignment.dao.AuthorDao
import com.dehaat.dehaatassignment.dao.BookDao
import com.dehaat.dehaatassignment.model.Author
import com.dehaat.dehaatassignment.model.Book


@Database(entities = arrayOf(Author::class,Book::class), version = 3)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun authorDao(): AuthorDao
    abstract fun bookDao():BookDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                AppDatabase::class.java, "dehaat_database").fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE
        }
    }

}
