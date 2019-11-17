package fr.imt.imt_killianmer

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListFragment : Fragment() {

    private lateinit var books : List<Book>

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: BookListAdapter
    private lateinit var listener: BookOnClickListener

    companion object {
        val TAG = "books"

        fun newInstance(books : List<Book>) : ListFragment {
            val myFragment = ListFragment()
            val args = Bundle()

            args.putParcelableArray(TAG, books.toTypedArray())

            myFragment.arguments = args

            return myFragment
        }
    }

    /**
     * On create : Get the data and display the list of books
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.list_fragment, container, false)

        books = arguments?.getParcelableArray("books")?.toList() as List<Book>
        viewAdapter  = BookListAdapter(books, listener::onClick)
        recyclerView = view.findViewById(R.id.recycler_view)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
            recyclerView.setHasFixedSize(true)
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as BookOnClickListener
    }

    interface BookOnClickListener {
        fun onClick(book : Book)
    }

}