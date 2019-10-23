package no.hamre.booklist.app.rest.controller

import no.hamre.booklist.app.rest.api.Book
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.ANY)
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
}