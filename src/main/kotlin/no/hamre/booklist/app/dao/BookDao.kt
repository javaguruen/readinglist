package no.hamre.booklist.app.dao

import no.hamre.booklist.app.model.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface BookDao {
    fun insert(book: Book): Long
    fun findAll(): List<Book>
}

interface BookRepository : CrudRepository<Book, Long>

@Repository
open class BookDaoImpl(@Autowired val repository: BookRepository)
    : BookDao {

    override
    fun insert(book: Book): Long {
        val persistedBook = repository.save(book)
        return persistedBook.id!!
    }

    override fun findAll(): List<Book> {
        return repository.findAll().toList()
    }
}