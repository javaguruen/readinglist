package no.hamre.booklist.app.dao

import no.hamre.booklist.app.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import javax.sql.DataSource

interface BookDao {
  fun insert(book: Book): Book
  fun findAll(): List<Book>
  fun insertRawBookInfo(info: String): Long
}

interface AuthorRepository : CrudRepository<Author, Long>
interface BookRepository : CrudRepository<Book, Long>
interface TagRepository : CrudRepository<Tag, String>
interface TaggingRepository : CrudRepository<Tagging, Long>
interface UserRepository : CrudRepository<User, Long>

@Repository
class BookDaoImpl(@Autowired val repository: BookRepository, @Autowired val dataSource: DataSource)
  : BookDao {

  private val jdbcTemplate = JdbcTemplate(dataSource)

  override
  fun insert(book: Book): Book {
//    book.copy( authors = book.authors.map { authorRepository.save(it) }.toSet() )
    return repository.save(book)
  }

  override
  fun insertRawBookInfo(info: String): Long {
    val inserter = SimpleJdbcInsert(dataSource)
        .withTableName("raw_book_info")
        .usingGeneratedKeyColumns("id")
    val newId = inserter.executeAndReturnKey(mapOf("data" to info, "created" to LocalDateTime.now()))
    return newId.toLong()
  }

  override fun findAll(): List<Book> {
    return repository.findAll().toList()
  }
}