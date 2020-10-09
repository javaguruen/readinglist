package no.hamre.booklist.app.rest.controller

import io.swagger.v3.oas.annotations.tags.Tag
import no.hamre.booklist.app.rest.api.Api2ModelMapper
import no.hamre.booklist.app.rest.api.Author
import no.hamre.booklist.app.rest.api.Model2ApiMapper
import no.hamre.booklist.app.service.AuthorService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.created
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import javax.validation.Valid

@RequestMapping(
    path = ["/api/v1/authors"],
    produces = [APPLICATION_JSON_VALUE],
    name = "Author"
)
@RestController
@Tag(name="Authors")
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