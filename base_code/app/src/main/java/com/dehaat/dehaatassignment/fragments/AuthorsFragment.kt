package com.dehaat.dehaatassignment.fragments


import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dehaat.dehaatassignment.R
import com.dehaat.dehaatassignment.activity.MainActivity
import com.dehaat.dehaatassignment.adapter.AuthorAdapter
import com.dehaat.dehaatassignment.database.AppDatabase
import com.dehaat.dehaatassignment.listener.LogoutListener
import com.dehaat.dehaatassignment.model.Author
import com.dehaat.dehaatassignment.model.AuthorsData
import com.dehaat.dehaatassignment.model.AuthorsResponseDto
import com.dehaat.dehaatassignment.model.Book
import com.dehaat.dehaatassignment.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.fragment_authors.*
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class AuthorsFragment : BaseFragment() {

    private var recyclerAdapter: AuthorAdapter? = null
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var listener: LogoutListener
    private lateinit var myViewModel: MyViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as LogoutListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_authors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        getAuthorsList();

    }

    private fun initViews() {
        var toolbar = (activity as MainActivity).supportActionBar
        toolbar?.title = getString(R.string.authors)
        toolbar?.setDisplayShowHomeEnabled(true)
        toolbar?.setDisplayShowTitleEnabled(true)

    }

    private fun getAuthorsList() {

        myViewModel = activity?.application?.let { MyViewModel(it) }!!
        myViewModel.getAuthorList().observe(viewLifecycleOwner, Observer { t ->
            t?.data?.let {
                setRecyclerView(it)
                myViewModel.setAuthorInfo(t)
            }
        })
    }

    private fun setRecyclerView(data: List<AuthorsData>) {
        layoutManager = LinearLayoutManager(activity)
        recyclerAdapter = activity?.let { AuthorAdapter(it, data) }
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = recyclerAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
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


