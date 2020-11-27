package no.hamre.booklist.app.rest.controller

import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.tags.Tags
import no.hamre.booklist.app.rest.api.Api2ModelMapper
import no.hamre.booklist.app.rest.api.Book
import no.hamre.booklist.app.rest.api.Model2ApiMapper
import no.hamre.booklist.app.service.BookService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RequestMapping(path = ["/api/v1/books"], produces = [MediaType.APPLICATION_JSON_VALUE])
@RestController
class BookControllerImpl @Autowired constructor(val bookService: BookService) : BookController {
  private val baseUrl = "http://localhost:8080/api/v1/books"

  override fun addRawTextAsBody(body: String?): ResponseEntity<Long> {
    log.info("Received raw body: $body")
    val newId: Long = bookService.addRawBookInfo(body!!)
    return ResponseEntity.created(URI("$baseUrl/raw/$newId"))
        .contentType(MediaType.TEXT_PLAIN)
        .body(newId)
  }

  override fun addBook(book: Book): ResponseEntity<Book> {
    log.info("Add book $book")
    val modelBook = bookService.addBook(Api2ModelMapper.mapBook(book))
    return ResponseEntity.created(URI("$baseUrl/"))
        .body(Model2ApiMapper.mapBook(modelBook))
  }

  override fun updateBook(id: Long, book: Book): ResponseEntity<Book> {
    TODO("Not yet implemented")
  }

  override fun listBooks(): ResponseEntity<Array<Book>> {
    val modelBooks = bookService.listBooks()
    //modelBooks.forEach { println(it) }
    return ResponseEntity.ok(Model2ApiMapper.mapBooks(modelBooks))
  }

  companion object {
    private val log: Logger = LoggerFactory.getLogger(BookController::class.java)
  }
}