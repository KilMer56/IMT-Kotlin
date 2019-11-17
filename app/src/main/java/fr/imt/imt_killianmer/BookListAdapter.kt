package fr.imt.imt_killianmer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class BookListAdapter internal constructor(private var books: List<Book>, private val onBookClick: (book: Book) -> Unit) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    /**
     * Display the information for a book in the list
     */
    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindBook(book: Book, onBookClick: (book: Book) -> Unit) {
            val bookTitle: TextView = itemView.findViewById(R.id.bookItemTitle)
            bookTitle.text = book.title

            val bookPrice: TextView = itemView.findViewById(R.id.bookItemPrice)
            bookPrice.text = book.price + " €"

            val bookCover = itemView.findViewById<ImageView>(R.id.bookItemCover)

            if(bookCover != null) {
                Picasso.get().load(book.cover).into(bookCover)
            }

            itemView.setOnClickListener {
                onBookClick(book)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val current = books[position]
        holder.bindBook(current, onBookClick)
    }

    override fun getItemCount() = books.size
}