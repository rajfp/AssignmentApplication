package com.dehaat.dehaatassignment.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dehaat.dehaatassignment.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * A simple [Fragment] subclass.
 */
open class BaseFragment : Fragment(),CoroutineScope {

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job= Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}
