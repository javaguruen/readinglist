package no.hamre.booklist.app.service

import no.hamre.booklist.app.dao.AuthorDao
import no.hamre.booklist.app.dao.BookDao
import no.hamre.booklist.app.model.Author
import no.hamre.booklist.app.model.Book
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

interface BookService {
  fun addBook(book: Book): Book
  fun addRawBookInfo(info: String): Long
  fun listBooks(): List<Book>
}

@Service
@Transactional
class BookServiceImpl @Autowired constructor(val dao: BookDao, val authorDao: AuthorDao)
  : BookService {

  override
  fun addBook(book: Book): Book {
    //Add or instert authors
    val persistedAuthors = book.authors.map { author -> map2managedAuthors(author) }.toSet()
    return dao.insert(book.copy(authors = persistedAuthors))
  }

  private fun map2managedAuthors(author: Author): Author {
    var persistedAuthor: Author? = null
    if (author.id != null) authorDao.findAuthorById(id = author.id)
    if (persistedAuthor == null) {
      persistedAuthor = authorDao.findAuthorByName(author.firstName, author.lastName)
    }
    if (persistedAuthor == null){
      persistedAuthor = authorDao.insertAuthor(author)
    }
    return persistedAuthor
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

  companion object {
    private val log: Logger = LoggerFactory.getLogger(BookService::class.java)
  }

}