package no.hamre.booklist.app.service

import no.hamre.booklist.app.dao.BookDao
import no.hamre.booklist.app.log
import no.hamre.booklist.app.model.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

interface BookService{
    fun addBook(book: Book): Book
    fun addRawBookInfo(info: String): Long
    fun listBooks(): List<Book>
}

@Service
@Transactional
class BookServiceImpl @Autowired constructor(val dao: BookDao)
    : BookService {

    override
    fun addBook(book: Book): Book {
        return dao.insert(book)
    }

    override fun addRawBookInfo(info: String): Long {
        val id = dao.insertRawBookInfo(info)
        log.info("New ID is $id")
        return id
    }

    override fun listBooks(): List<Book> {
        val books = dao.findAll()
        //books.forEach { println( it.authors.forEach{ aut -> println(aut.firstName )} ) }
        return books
    }
}