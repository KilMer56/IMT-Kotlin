package fr.imt.imt_killianmer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    private lateinit var book: Book

    companion object {
        val TAG = "book"

        fun newInstance(book: Book): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()

            args.putParcelable(TAG, book)
            fragment.arguments = args

            return fragment
        }
    }

    /**
     * On create : Get the data and display the informations of the book
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)

        val newBook: Book? = arguments?.getParcelable(TAG)

        if(newBook != null){
            book = newBook
        }

        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.details_fragment, container, false)

        view.findViewById<TextView>(R.id.book_detail_title).text = book.title
        view.findViewById<TextView>(R.id.book_details_description).text = book.synopsis!!.joinToString()
        view.findViewById<TextView>(R.id.book_detail_price).text = "Prix : " + book.price + "€"

        val bookCover = view.findViewById<ImageView>(R.id.book_details_cover)

        if(bookCover != null) {
            Picasso.get().load(book.cover).into(bookCover)
        }

        return view
    }
}