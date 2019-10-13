package no.hamre.booklist.app.service

import no.hamre.booklist.app.dao.BookDao
import no.hamre.booklist.app.model.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface BookService{
    fun addBook(book: Book): Long
    fun listBooks(): List<Book>
}

@Service
class BookServiceImpl @Autowired constructor(val dao: BookDao)
    : BookService {

    override
    fun addBook(book: Book): Long {
        return dao.insert(book)
    }

    override fun listBooks(): List<Book> {
        return dao.findAll()
    }
}