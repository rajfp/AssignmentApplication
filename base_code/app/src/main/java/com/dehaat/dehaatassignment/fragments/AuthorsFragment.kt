package com.dehaat.dehaatassignment.fragments


import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.dehaat.dehaatassignment.R
import com.dehaat.dehaatassignment.adapter.AuthorAdapter
import com.dehaat.dehaatassignment.database.AppDatabase
import com.dehaat.dehaatassignment.model.Author
import com.dehaat.dehaatassignment.model.AuthorsResponseDto
import com.dehaat.dehaatassignment.model.Book
import com.dehaat.dehaatassignment.rest.AppRestClient
import com.dehaat.dehaatassignment.rest.AppRestClientService
import kotlinx.android.synthetic.main.fragment_authors.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

/**
 * A simple [Fragment] subclass.
 */
class AuthorsFragment : Fragment() {

    private  var recyclerAdapter:AuthorAdapter?=null
    private lateinit var layoutManager:RecyclerView.LayoutManager
    private var appDatabase:AppDatabase?=null


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
                    saveData(response.body())
            }


        })
    }

    private fun saveData(body: AuthorsResponseDto?) {
        appDatabase= activity?.let { AppDatabase.getDatabase(it) }
        activity?.let { InsertTask(body).execute() }
        activity?.let { GetAuthors(it).execute() }
    }

    inner class InsertTask(private val body: AuthorsResponseDto?): AsyncTask<Void, Void, Boolean>() {
        override fun doInBackground(vararg params: Void?): Boolean {
            for (i in body?.data!!) {
                appDatabase?.authorDao()?.insertAllAuthors(Author(i.author_name,i.author_bio))
                appDatabase?.bookDao()?.insertAll(Book(i.author_name,i.books))
            }
            return true
        }

        override fun onPostExecute(result: Boolean?) {
            super.onPostExecute(result)
            Toast.makeText(activity,"Added Successfully to database",Toast.LENGTH_LONG).show()
        }
    }

    inner class GetAuthors(private var context: Context) :  AsyncTask<Void, Void, Boolean>(){
        override fun doInBackground(vararg params: Void?): Boolean {
            var list=appDatabase?.authorDao()?.authorsList

            for (i in list!!) {
                Log.d("Author Name", i.author_name)
                var bookList=appDatabase?.bookDao()?.getBooks(i.author_name)
                Log.d("Books by ${i.author_name}",bookList.toString())
            }
            return true
        }
    }
}


