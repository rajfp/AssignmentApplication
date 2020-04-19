package com.dehaat.dehaatassignment.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log_but.setOnClickListener{

            var fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, AuthorsFragment())
            ?.addToBackStack(null)
            fragmentTransaction?.commit()
        }
    }

}
