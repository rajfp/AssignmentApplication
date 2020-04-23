package com.dehaat.dehaatassignment.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dehaat.dehaatassignment.model.AuthorsResponseDto
import com.dehaat.dehaatassignment.repository.MyRepository

class MyViewModel(private val application: Application): ViewModel() {
    private var myRepository:MyRepository
    private var authorData: MutableLiveData<AuthorsResponseDto>

    init {
        myRepository= MyRepository(application)
        authorData= MutableLiveData()
    }

    fun getAuthorList():MutableLiveData<AuthorsResponseDto>{
        authorData=myRepository.getAuthorList()
        return authorData
    }

    fun setAuthorInfo(authorsResponseDto: AuthorsResponseDto?)=myRepository.saveAuthorInfo(authorsResponseDto)

     fun getBookList(authorName: String?)=myRepository.fetchBooks(authorName)

}