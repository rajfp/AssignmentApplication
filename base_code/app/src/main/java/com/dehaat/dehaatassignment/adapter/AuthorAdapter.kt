package com.dehaat.dehaatassignment.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dehaat.dehaatassignment.R
import com.dehaat.dehaatassignment.activity.MainActivity
import com.dehaat.dehaatassignment.fragments.BooksFragment
import com.dehaat.dehaatassignment.model.AuthorsData
import kotlinx.android.synthetic.main.author_item_layout.view.*

class AuthorAdapter(private val context: Context,private val authorList: List<AuthorsData>?) : RecyclerView.Adapter<AuthorAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(LayoutInflater.from(context).inflate(R.layout.author_item_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return authorList?.size!!
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        holder.authorName.text=authorList?.get(position)?.author_name
        holder.authorBio.text=authorList?.get(position)?.author_bio
        holder.clickView.setOnClickListener{
            Toast.makeText(context,authorList?.get(position)?.author_name,Toast.LENGTH_LONG).show()
            var ctx= context as MainActivity
            var booksFragment=BooksFragment()
            var bundle=Bundle()
            bundle.putString("Author_Name",authorList?.get(position)?.author_name)
            booksFragment.arguments=bundle
            ctx?.supportFragmentManager.beginTransaction().replace(R.id.fragment_container,booksFragment,null).
            addToBackStack(null).commit()
        }

    }

    class MyViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val authorName= view.tv_author_name
        val authorBio= view.tv_author_bio
        val clickView=view.click_view
    }
}