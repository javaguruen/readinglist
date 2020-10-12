package no.hamre.booklist.app.dao

import no.hamre.booklist.app.exception.NotFoundException
import no.hamre.booklist.app.model.Author
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

interface AuthorDao {
  fun insertAuthor(author: Author): Author
  fun findAuthorById(id: Long): Author
  fun findAuthorByName(firstName: String, lastName: String): Author?
  fun allAuthors(): List<Author>
}

@Repository
class AuthorDaoImpl(private val authorRepository: AuthorRepository) : AuthorDao {

  override fun insertAuthor(author: Author): Author {
    return authorRepository.save(author)
  }

  override fun findAuthorById(id: Long): Author {
    return authorRepository.findById(id).orElseThrow { NotFoundException("Did not find Author with ID: $id") }
  }

  override fun findAuthorByName(firstName: String, lastName: String): Author? {
    val author = authorRepository.findByFirstNameAndLastName(firstName, lastName)
    if (author == null) {
      LOG.info("Did not find Author with first name: $firstName and last name $lastName")
    } else {
      LOG.info("Found Author with id: ${author.id} first name: $firstName and last name $lastName")
    }
    return author
  }

  override fun allAuthors(): List<Author> {
    return authorRepository.findAll().toList()
  }

  companion object {
    val LOG: Logger = LoggerFactory.getLogger(AuthorDaoImpl::class.java)
  }
}