package com.dehaat.dehaatassignment.fragments


import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dehaat.dehaatassignment.R
import com.dehaat.dehaatassignment.activity.MainActivity
import com.dehaat.dehaatassignment.adapter.BooksAdapter
import com.dehaat.dehaatassignment.database.AppDatabase
import com.dehaat.dehaatassignment.listener.LogoutListener
import com.dehaat.dehaatassignment.model.Constants
import com.dehaat.dehaatassignment.model.books
import kotlinx.android.synthetic.main.fragment_books.*
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class BooksFragment : BaseFragment() {

    private var appDatabase: AppDatabase? = null
    private lateinit var authorData: List<books>
    private lateinit var listener:LogoutListener



    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener=context as LogoutListener
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        fetchBooksList()
    }

    private fun initViews() {
        var toolbar = (activity as MainActivity).supportActionBar
        toolbar?.title = getString(R.string.books)
        toolbar?.setDisplayShowHomeEnabled(true)
        toolbar?.setDisplayShowTitleEnabled(true)

    }

    private fun fetchBooksList() {
        appDatabase = activity?.let { AppDatabase.getDatabase(it) }
        authorData=ArrayList()
        launch {
            authorData = arguments?.getString(Constants.author_name)?.let {
                appDatabase?.bookDao()?.getBooks(it)?.bookList

            }!!
            var booksAdapter = activity?.let { BooksAdapter(it, authorData) }
            var layoutManager = LinearLayoutManager(context)
            recycler_view_books.layoutManager = layoutManager
            recycler_view_books.adapter = booksAdapter
        }

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.mymenu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout_btn -> {
                listener?.onLogout()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
