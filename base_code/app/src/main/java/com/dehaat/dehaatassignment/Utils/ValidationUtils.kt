package com.dehaat.dehaatassignment.Utils

import android.widget.EditText

class ValidationUtils {
    companion object{
    fun validateUsername(username:EditText):Boolean?{
            val content=username.text.toString()
            username.error=if(content.length>3)null else "Minimum length of Username should be 3"
            return (username.error==null)
        }

        fun validatePassword(password:EditText):Boolean?{
            val content=password.text.toString()
            password.error=if(content.length>8)null else "Minimum length of Password should be 8"
            return  (password.error==null)
        }
    }
}

