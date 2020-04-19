package com.dehaat.dehaatassignment.fragments


import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.dehaat.dehaatassignment.R
import com.dehaat.dehaatassignment.activity.MainActivity
import com.dehaat.dehaatassignment.adapter.BooksAdapter
import com.dehaat.dehaatassignment.database.AppDatabase
import com.dehaat.dehaatassignment.model.Constants
import com.dehaat.dehaatassignment.model.books
import kotlinx.android.synthetic.main.fragment_books.*

/**
 * A simple [Fragment] subclass.
 */
class BooksFragment : Fragment()  {

    private var appDatabase: AppDatabase?=null
    private lateinit var authorData:List<books>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchBooksList()
    }

    private fun fetchBooksList() {
        appDatabase=activity?.let { AppDatabase.getDatabase(it) }
        arguments?.getString(Constants.author_name)?.let { BookTask(activity as MainActivity, it).execute() }
    }

        inner class BookTask(private val context: MainActivity,private val authorName:String) : AsyncTask<Void,Void,Boolean>() {
            override fun doInBackground(vararg params: Void?): Boolean {
                authorData= appDatabase?.bookDao()?.getBooks(authorName)?.bookList!!
                return true
            }

            override fun onPostExecute(result: Boolean?) {
                super.onPostExecute(result)
                var booksAdapter=BooksAdapter(context,authorData)
                var layoutManager=LinearLayoutManager(context)
                recycler_view_books.layoutManager=layoutManager
                recycler_view_books.adapter=booksAdapter

            }
        }
}
