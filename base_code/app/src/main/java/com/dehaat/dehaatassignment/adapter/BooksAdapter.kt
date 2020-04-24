package com.dehaat.dehaatassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dehaat.dehaatassignment.R
import com.dehaat.dehaatassignment.model.books
import kotlinx.android.synthetic.main.books_item_layout.view.*

class BooksAdapter(private val context: Context, private val bookList: List<books>) : RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(LayoutInflater.from(context).inflate(R.layout.books_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return bookList?.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.title.text = bookList?.get(position).title
        holder.description.text = bookList?.get(position).description
        holder.price.text = "Price: Rs "+bookList?.get(position).price
        if (bookList.get(position).isExpanded) {
            holder.description.visibility = View.VISIBLE
            holder.price.visibility=View.VISIBLE
            holder.imgView.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp)
        }
        else {
            holder.description.visibility = View.GONE
            holder.price.visibility=View.GONE
            holder.imgView.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp)
        }
        holder.clickViewbooks.setOnClickListener {
            bookList.get(position).isExpanded = !bookList.get(position).isExpanded
            notifyItemChanged(position)
        }

    }

    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var title = view.tv_title
        var description = view.tv_description
        var price = view.tv_price
        var clickViewbooks = view.click_view_books
        var imgView=view.img
    }
}