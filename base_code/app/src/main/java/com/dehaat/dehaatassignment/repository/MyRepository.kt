package com.dehaat.dehaatassignment.repository

import androidx.lifecycle.MutableLiveData
import com.dehaat.dehaatassignment.model.AuthorsResponseDto
import com.dehaat.dehaatassignment.rest.AppRestClient
import com.dehaat.dehaatassignment.rest.AppRestClientService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyRepository {

    private var appRestClientService:AppRestClientService
    private var authorData:MutableLiveData<AuthorsResponseDto>

    init {
        appRestClientService= AppRestClient.getInstance().create(AppRestClientService::class.java)
        authorData= MutableLiveData()
    }

    fun  getAuthorList():MutableLiveData<AuthorsResponseDto>{
        var response=appRestClientService.fetchListOfAuthors()
        response.enqueue(object : Callback<AuthorsResponseDto> {
            override fun onFailure(call: Call<AuthorsResponseDto>, t: Throwable) {
                authorData.value=null
            }

            override fun onResponse(call: Call<AuthorsResponseDto>, response: Response<AuthorsResponseDto>) {
                authorData.value=response.body()
            }

        })
        return authorData
    }
}