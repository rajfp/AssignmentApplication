package com.dehaat.dehaatassignment.fragments


import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dehaat.dehaatassignment.R
import com.dehaat.dehaatassignment.database.AppDatabase

/**
 * A simple [Fragment] subclass.
 */
class BooksFragment : Fragment()  {

    private var appDatabase: AppDatabase?=null

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
        arguments?.getString("Author_Name")?.let { BookTask(it).execute() }
    }

        inner class BookTask(private val authorName:String) : AsyncTask<Void,Void,Boolean>() {
            override fun doInBackground(vararg params: Void?): Boolean {
                val authorData=appDatabase?.bookDao()?.getBooks(authorName)?.bookList
                return true
            }
        }
}
