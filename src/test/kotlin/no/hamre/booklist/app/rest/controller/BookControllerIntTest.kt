package no.hamre.booklist.app.rest.controller

import com.fasterxml.jackson.databind.JsonNode
import no.hamre.booklist.app.ObjectMapperFactory
import no.hamre.booklist.app.rest.api.*
import org.junit.jupiter.api.Assertions.*
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
    val requestBook = Book(
        authors = listOf(Author(firstName = "Stephen", lastName = "King")),
        originalTitle = "Carrie",
        norwegianTitle = "Carrie",
        language = "English",
        link = "https://www.amazon.com/Carrie-Stephen-King/dp/0307743667",
        medium = Medium.PAPIR,
        readingOrder = 0,
        tags = setOf(
            Tag(TagName("Read")), Tag(TagName("Horror")))
        )

    val resultStatus = testRestTemplate
        .postForEntity(
            "/api/v1/books",
            requestBook,
            String::class.java
            //Book::class.java
        )
    assertNotNull(resultStatus)
    assertEquals(HttpStatus.CREATED, resultStatus.statusCode)
    val bookString = resultStatus.body
    val book = ObjectMapperFactory.create().readValue(bookString, Book::class.java)
    assertNotNull(book)
    assertEquals(1, book?.authors?.size, "Should have one author")
    assertEquals("Carrie", book?.originalTitle)
    assertEquals(requestBook.norwegianTitle, book?.norwegianTitle, "Norwegian title")
    assertEquals(requestBook.originalTitle, book?.originalTitle, "Original title")
    assertEquals(requestBook.language, book?.language, "Language")
    //assertEquals(requestBook.link, book?.link, "Link")
    assertEquals(requestBook.medium, book?.medium, "Medium")
    assertEquals(requestBook.readingOrder, book?.readingOrder, "Reading order")
    assertEquals(2, requestBook.tags.size, "Tag size")
    assertTrue(requestBook.tags.any { it.name.name == "Read" }, "Tag: Read" )
    assertTrue(requestBook.tags.any { it.name.name == "Horror" } , "Tag: Horror")
  }
}