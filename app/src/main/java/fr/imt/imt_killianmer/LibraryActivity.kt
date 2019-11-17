package fr.imt.imt_killianmer

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LibraryActivity : AppCompatActivity(), ListFragment.BookOnClickListener {

    private var books : List<Book>? = null
    private var currentBook: Book? = null

    private val tagBook : String = "book"
    private val tagList : String = "books"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                books = BooksRepository().getBooks()
            }

            if(books != null){
                openListTab(books!!)
            }
            else {
                showToast("Erreur récupération des livres")
            }
        }
    }

    /**
     * On click method on an element of the list
     */
    override fun onClick(book: Book) {
        this.currentBook = book

        // Open the detail's fragment
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            this.openDetailTab(book, R.id.detail_container)
        }
        else {
            this.openDetailTab(book, R.id.container)
        }
    }

    /**
     * Send a transaction to the list fragment
     */
    private fun openListTab(books: List<Book>){
        supportFragmentManager.beginTransaction().run {
            replace(
                R.id.container,
                ListFragment.newInstance(books),
                ListFragment::class.java.name
            )
            commit()
        }
    }

    /**
     * Send a transaction to the detail fragment
     */
    private fun openDetailTab(book: Book, id: Int){
        supportFragmentManager.beginTransaction().run {
            replace(
                id,
                DetailsFragment.newInstance(book),
                DetailsFragment::class.java.name
            )
            addToBackStack(DetailsFragment::class.java.name)
            commit()
        }
    }

    /**
     * Display a toast with a custom message
     */
    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Save the state when we change the orientation of the phone
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(tagBook, this.currentBook)
        outState.putParcelableArray(tagList, this.books?.toTypedArray())
    }

    /**
     * Restore the state in order to keep our data
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        currentBook = savedInstanceState?.getParcelable(tagBook)
        books = savedInstanceState?.getParcelableArray(tagList)?.toList() as List<Book>

        // Get the list of books
        if(books != null){
            openListTab(books!!)
        }
        else {
            showToast("Erreur récupération des livres")
        }

        // Get the current book
        if(currentBook != null){
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                this.openDetailTab(currentBook!!, R.id.detail_container)
            }
            else {
                this.openDetailTab(currentBook!!, R.id.container)
            }
        }
    }
}
