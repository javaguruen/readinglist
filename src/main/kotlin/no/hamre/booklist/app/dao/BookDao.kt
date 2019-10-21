package no.hamre.booklist.app.dao

import no.hamre.booklist.app.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface BookDao {
    fun insert(book: Book): Long
    fun findAll(): List<Book>
}

interface AuthorRepository : CrudRepository<Author, Long>
interface BookRepository : CrudRepository<Book, Long>
interface TagRepository : CrudRepository<Tag, String>
interface TaggingRepository : CrudRepository<Tagging, Long>
interface UserRepository : CrudRepository<User, Long>

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