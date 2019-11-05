package no.hamre.booklist.app.dao

import no.hamre.booklist.app.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface BookDao {
  fun insert(book: Book): Book
  fun findAll(): List<Book>
}

interface AuthorRepository : CrudRepository<Author, Long>
interface BookRepository : CrudRepository<Book, Long>
interface TagRepository : CrudRepository<Tag, String>
interface TaggingRepository : CrudRepository<Tagging, Long>
interface UserRepository : CrudRepository<User, Long>

@Repository
class BookDaoImpl(@Autowired val repository: BookRepository, @Autowired val authorRepository: AuthorRepository)
  : BookDao {

  override
  fun insert(book: Book): Book {
//    book.copy( authors = book.authors.map { authorRepository.save(it) }.toSet() )
    return repository.save(book)
  }

  override fun findAll(): List<Book> {
    return repository.findAll().toList()
  }
}