package com.dehaat.dehaatassignment.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Book(@PrimaryKey(autoGenerate = true) val uid:Int,
                @ColumnInfo val title: String,
                @ColumnInfo val description:String,
                @ColumnInfo val price:String)
