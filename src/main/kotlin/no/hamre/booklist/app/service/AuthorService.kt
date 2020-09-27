package no.hamre.booklist.app.service

import no.hamre.booklist.app.dao.AuthorDao
import no.hamre.booklist.app.model.Author
import org.springframework.stereotype.Service

interface AuthorService {
  fun addAuthor(author: Author): Author

}

@Service
class AuthorServiceImpl(val authorDao: AuthorDao) : AuthorService {
  override fun addAuthor(author: Author): Author {
    return authorDao.insertAuthor(author)
  }
}