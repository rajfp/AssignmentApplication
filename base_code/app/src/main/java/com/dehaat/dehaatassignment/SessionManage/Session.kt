package com.dehaat.dehaatassignment.SessionManage

import android.content.Context
import android.content.SharedPreferences

 data class SessionManager(private val context: Context) {

    private var pref:SharedPreferences
    private var isLoggedIN:String="isLoggedIN"


     init {
         pref=context.getSharedPreferences("Session",Context.MODE_PRIVATE)
     }
    fun createLoginSession(){
       pref.edit().putBoolean(isLoggedIN,true).commit();

    }
    fun isLoggedIn():Boolean{
        return pref.getBoolean(isLoggedIN,false)
    }
    fun destroySession(){
        pref.edit().clear().commit()
    }

}