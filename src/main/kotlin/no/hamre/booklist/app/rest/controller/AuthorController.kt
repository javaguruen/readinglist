package no.hamre.booklist.app.rest.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import no.hamre.booklist.app.rest.api.Api2ModelMapper
import no.hamre.booklist.app.rest.api.Author
import no.hamre.booklist.app.rest.api.Book
import no.hamre.booklist.app.rest.api.Model2ApiMapper
import no.hamre.booklist.app.service.AuthorService
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

@RequestMapping(path = ["/api/v1/authors"], produces = [APPLICATION_JSON_VALUE])
@RestController
class AuthorController @Autowired constructor(val authorService: AuthorService) {
  private val baseUrl = "http://localhost:8080/api/v1/authors"

  @RequestMapping(method = [POST])
  fun addAuthor(@RequestBody @Valid author: Author): ResponseEntity<Author> {
    log.info("Add author $author")
    val modelAuthor = authorService.addAuthor(Api2ModelMapper.mapAuthor(author))
    return created(URI("$baseUrl/"))
        .body(Model2ApiMapper.mapAuthor(modelAuthor))
  }

  companion object {
    private val log: Logger = LoggerFactory.getLogger(AuthorController::class.java)
  }
}