package no.hamre.booklist.app.rest.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import no.hamre.booklist.app.rest.api.Api2ModelMapper
import no.hamre.booklist.app.rest.api.Book
import no.hamre.booklist.app.rest.api.Model2ApiMapper
import no.hamre.booklist.app.service.BookService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.*
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.created
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import javax.validation.Valid

@RequestMapping(path = ["/api/v1/books"], produces = [APPLICATION_JSON_VALUE])
@RestController
class BookController @Autowired constructor(val bookService: BookService) {
  private val baseUrl = "http://localhost:8080/api/v1/books"

  @RequestMapping(path = ["/raw"], method = [POST], consumes = [TEXT_PLAIN_VALUE], produces = [TEXT_PLAIN_VALUE])
  @Operation(
      security = [
        SecurityRequirement(name = "bearerToken", scopes = ["book.write"])
      ]
  )
  @ApiResponses(value = [
    ApiResponse(responseCode = "201", description = "Successfully persisted.",
        content = [Content(schema = Schema(implementation = Long::class))])
  ])
  fun addRawTextAsBody(@RequestBody body: String?): ResponseEntity<Long> {
    log.info("Received raw body: $body")
    val newId: Long = bookService.addRawBookInfo(body!!)
    return created(URI("$baseUrl/raw/$newId"))
        .contentType(TEXT_PLAIN)
        .body(newId)
  }

  @RequestMapping(method = [POST])
  fun addBook(@RequestBody @Valid book: Book): ResponseEntity<Book> {
    log.info("Add book $book")
    val modelBook = bookService.addBook(Api2ModelMapper.mapBook(book))
    return created(URI("$baseUrl/"))
        .body(Model2ApiMapper.mapBook(modelBook))
  }

  @RequestMapping(method = [GET], path = ["/"], produces = [APPLICATION_JSON_VALUE])
  fun listBooks(): ResponseEntity<Array<Book>> {
    val modelBooks = bookService.listBooks()
    //modelBooks.forEach { println(it) }
    return ok(Model2ApiMapper.mapBooks(modelBooks))
  }

  companion object {
    private val log: Logger = LoggerFactory.getLogger(BookController::class.java)
  }
}