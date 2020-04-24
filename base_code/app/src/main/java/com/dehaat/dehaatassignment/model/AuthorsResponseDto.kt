package com.dehaat.dehaatassignment.model


import com.google.gson.annotations.SerializedName

data class AuthorsResponseDto(@SerializedName("data") var data: List<AuthorsData>?, var message:String?)

data class AuthorsData(var author_name:String?,var author_bio:String?,var books: List<books>?)

data class books(var publisher:String?,var title:String?,var description:String?,var price:String?,var isExpanded:Boolean=false)