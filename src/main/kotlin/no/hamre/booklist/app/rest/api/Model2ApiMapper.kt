package no.hamre.booklist.app.rest.api

object Model2ApiMapper {
  fun mapBooks(books: List<no.hamre.booklist.app.model.Book>): Array<Book> {
    return books.map { mapBook(it) }.toTypedArray()
  }

  fun mapBook(book: no.hamre.booklist.app.model.Book): Book {
    return Book(
        id = book.id,
        authors = book.authors.map { mapAuthor(it) },
        originalTitle = book.originalTitle,
        norwegianTitle = book.norwegianTitle,
        language = book.language,
        link = null,
        medium = mapMedium(book.medium),
        tags = mapTags(book.tags)
    )
  }

  private fun mapMedium(medium: no.hamre.booklist.app.model.Medium?): Medium? {
    return when (medium) {
      null -> null
      no.hamre.booklist.app.model.Medium.PAPIR -> Medium.PAPIR
      no.hamre.booklist.app.model.Medium.LYDBOK -> Medium.LYDBOK
      no.hamre.booklist.app.model.Medium.EBOK -> Medium.EBOK
    }

  }

  private fun mapTags(tags: Set<no.hamre.booklist.app.model.Tag>): Set<Tag> {
    return tags.map { t -> Tag(TagName(name = t.name)) }.toSet()
  }

  fun mapAuthor(author: no.hamre.booklist.app.model.Author): Author {
    return Author(id = author.id,
        firstName = author.firstName,
        lastName = author.lastName)
  }

}