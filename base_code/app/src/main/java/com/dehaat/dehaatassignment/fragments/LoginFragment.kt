package com.dehaat.dehaatassignment.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dehaat.dehaatassignment.R
import com.dehaat.dehaatassignment.model.AuthorsResponseDto
import com.dehaat.dehaatassignment.rest.AppRestClient
import com.dehaat.dehaatassignment.rest.AppRestClientService
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    var restService:AppRestClient?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log_but.setOnClickListener{
            //validateUser(et_email.text.toString(),et_password.text.toString())
            getAuthorsList();
        }
    }

    private fun getAuthorsList() {
        AppRestClient.setAppRestClientNull()
        val appRestClientService=AppRestClient.getInstance().create(AppRestClientService::class.java)
        val response = appRestClientService.fetchListOfAuthors()
        response.enqueue( object:Callback<AuthorsResponseDto>{
            override fun onFailure(call: Call<AuthorsResponseDto>, t: Throwable) {
            Log.d("TAG", t.message)
            }

            override fun onResponse(call: Call<AuthorsResponseDto>, response: Response<AuthorsResponseDto>) {
                Log.d("Success",response.toString())
            }


        })
    }


}
