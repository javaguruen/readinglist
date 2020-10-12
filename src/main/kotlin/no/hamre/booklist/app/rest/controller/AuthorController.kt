package no.hamre.booklist.app.rest.controller

import io.swagger.v3.oas.annotations.tags.Tag
import no.hamre.booklist.app.rest.api.Author
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RequestMethod.POST
import javax.validation.Valid

@Tag(name = "Authors")
interface AuthorController {

  @RequestMapping(method = [POST])
  fun addAuthor(@RequestBody @Valid author: Author): ResponseEntity<Author>

  @RequestMapping(method = [GET])
  fun listAuthors(): ResponseEntity<List<Author>>

}