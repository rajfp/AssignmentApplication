package com.dehaat.dehaatassignment.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dehaat.dehaatassignment.R

/**
 * A simple [Fragment] subclass.
 */
class AuthorsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authors, container, false)
    }


}
