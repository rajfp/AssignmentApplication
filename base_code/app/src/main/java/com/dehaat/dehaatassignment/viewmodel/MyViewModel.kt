package com.dehaat.dehaatassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dehaat.dehaatassignment.model.AuthorsResponseDto
import com.dehaat.dehaatassignment.repository.MyRepository

class MyViewModel: ViewModel() {
    private var myRepository:MyRepository
    private var authorData: MutableLiveData<AuthorsResponseDto>

    init {
        myRepository= MyRepository()
        authorData= MutableLiveData()
    }

    fun getAuthorList():MutableLiveData<AuthorsResponseDto>{
        authorData=myRepository.getAuthorList()
        return authorData
    }
}