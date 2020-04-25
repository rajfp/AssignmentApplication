package com.dehaat.dehaatassignment.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.dehaat.dehaatassignment.R
import com.dehaat.dehaatassignment.Utils.ValidationUtils
import com.dehaat.dehaatassignment.activity.MainActivity
import com.dehaat.dehaatassignment.listener.SessionListener
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

    var sessionListener:SessionListener?=null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sessionListener=context as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        log_but.setOnClickListener{

            if(ValidationUtils.validateUsername(et_email)!! && ValidationUtils.validatePassword(et_password)!!) {
                sessionListener?.createSession()
                var fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.fragment_container, AuthorsFragment())
                fragmentTransaction?.commit()
            }
        }
    }

    private fun initViews() {
        var toolbar=(activity as MainActivity).supportActionBar
        toolbar?.title=getString(R.string.login)
        toolbar?.setDisplayShowHomeEnabled(true)
        toolbar?.setDisplayShowTitleEnabled(true)

    }

    override fun onPause() {
        super.onPause()
        log_but.setOnClickListener(null)
    }

}
