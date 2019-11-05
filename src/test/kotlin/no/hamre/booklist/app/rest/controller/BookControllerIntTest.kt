package no.hamre.booklist.app.rest.controller

import no.hamre.booklist.app.rest.api.Author
import no.hamre.booklist.app.rest.api.Book
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerIntTest {
  @Autowired
  lateinit var testRestTemplate: TestRestTemplate

  @Test
  fun `Get all books`() {
    val result = testRestTemplate.getForEntity("/api/v1/books", Array<Book>::class.java)
    assertNotNull(result)
    assertEquals(HttpStatus.OK, result.statusCode)
    val books = result.body
    assertEquals(2, books?.size)
    assertEquals("The Shining", books?.first()?.originalTitle)
  }

  @Test
  fun `Add new book`() {
    val result = testRestTemplate
        .postForEntity(
            "/api/v1/books",
            Book(
                authors = listOf(Author(firstName = "Stephen", lastName = "King")),
                originalTitle = "Carrie",
                norwegianTitle = "Carrie"
            ),
            //String::class.java
            Book::class.java
        )
    assertNotNull(result)
    assertEquals(HttpStatus.CREATED, result.statusCode)
    val book = result.body
    assertNotNull(book)
    assertEquals(1, book?.authors?.size, "Should have one author")
    assertEquals("Carrie", book?.originalTitle)
  }
}