package com.dehaat.dehaatassignment.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.dehaat.dehaatassignment.R
import com.dehaat.dehaatassignment.adapter.AuthorAdapter
import com.dehaat.dehaatassignment.model.AuthorsResponseDto
import com.dehaat.dehaatassignment.rest.AppRestClient
import com.dehaat.dehaatassignment.rest.AppRestClientService
import kotlinx.android.synthetic.main.fragment_authors.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class AuthorsFragment : Fragment() {

    private  var recyclerAdapter:AuthorAdapter?=null
    private lateinit var layoutManager:RecyclerView.LayoutManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       getAuthorsList();
    }
    private fun getAuthorsList() {
        AppRestClient.setAppRestClientNull()
        val appRestClientService= AppRestClient.getInstance().create(AppRestClientService::class.java)
        val response = appRestClientService.fetchListOfAuthors()
        response.enqueue( object: Callback<AuthorsResponseDto> {
            override fun onFailure(call: Call<AuthorsResponseDto>, t: Throwable) {
                Toast.makeText(activity,t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<AuthorsResponseDto>, response: Response<AuthorsResponseDto>) {
                   layoutManager= LinearLayoutManager(activity)
                   recyclerAdapter= activity?.let { AuthorAdapter(it,response.body()?.data) }
                    recycler_view.layoutManager=layoutManager
                    recycler_view.adapter=recyclerAdapter
            }


        })
    }


}
