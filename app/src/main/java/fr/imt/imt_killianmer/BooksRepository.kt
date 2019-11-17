package fr.imt.imt_killianmer

class BooksRepository {
    var client : BookService = retrofit

    suspend fun getBooks(): List<Book> = client.listBooks()
}