package com.dehaat.dehaatassignment.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Author(@PrimaryKey(autoGenerate = true) val uid:Int,
                  @ColumnInfo val author_name:String?,
                  @ColumnInfo val author_bio:String? ) {
    constructor(author_name:String?,author_bio: String?):this(0,author_name,author_bio)

}


