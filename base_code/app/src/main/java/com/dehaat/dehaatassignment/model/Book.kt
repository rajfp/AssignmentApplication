package com.dehaat.dehaatassignment.model

import androidx.room.*
import com.dehaat.dehaatassignment.Converter.Converters

@Entity
data class Book(@PrimaryKey(autoGenerate = true)
                val uid:Int,
                @ColumnInfo
                val author_name:String?,
                @ColumnInfo
                @TypeConverters(Converters::class)
                val bookList:List<books>?)
{
    constructor(author_name: String?,bookList: List<books>?):this(0,author_name,bookList)
}
