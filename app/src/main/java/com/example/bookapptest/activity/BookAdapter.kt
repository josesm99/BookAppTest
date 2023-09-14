package com.example.bookapptest.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookapptest.R
import com.example.bookapptest.databinding.ItemBookBinding
import com.example.bookapptest.model.Book

class BookAdapter : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private val bookList = mutableListOf<Book>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemBookBinding.bind(itemView)

        fun bind(book: Book) {
            binding.titleTextView.text = book.volumeInfo.title
            binding.descriptionTextView.text = book.volumeInfo.description

            book.volumeInfo.imageLink?.let {
                book.volumeInfo.imageLink?.let {
                    Glide.with(itemView.context)
                        .load(book.volumeInfo.imageLink.smallThumbnail)
                        .into(binding.thumbnailImageView)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = bookList[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun updateList(items: List<Book>) {
        bookList.clear()
        bookList.addAll(items)
        notifyDataSetChanged()
    }
}