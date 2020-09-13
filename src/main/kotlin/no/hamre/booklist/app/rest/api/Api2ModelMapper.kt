package no.hamre.booklist.app.rest.api

object Api2ModelMapper {
  fun mapBook(book: Book): no.hamre.booklist.app.model.Book {
    return no.hamre.booklist.app.model.Book(
        id = book.id,
        originalTitle = book.originalTitle,
        norwegianTitle = book.norwegianTitle,
        authors = mapAuthors(book.authors),
        readingOrder = book.readingOrder,
        medium = mapMedium(book.medium),
        language = book.language,
        tags = mapTags(book.tags)
    )
  }

  private fun mapTags(tags: Set<Tag>): Set<no.hamre.booklist.app.model.Tag> {
    return tags.map { t -> no.hamre.booklist.app.model.Tag(t.name.name, setOf()) }.toSet()
  }

  private fun mapMedium(medium: Medium?): no.hamre.booklist.app.model.Medium? {
    return when (medium) {
      null -> null
      Medium.PAPIR -> no.hamre.booklist.app.model.Medium.PAPIR
      Medium.LYDBOK -> no.hamre.booklist.app.model.Medium.LYDBOK
      Medium.EBOK -> no.hamre.booklist.app.model.Medium.EBOK
    }
  }

  fun mapAuthors(authors: List<Author>): Set<no.hamre.booklist.app.model.Author> {
    return authors.map { mapAuthor(it) }.toSet()
  }

  fun mapAuthor(author: Author): no.hamre.booklist.app.model.Author {
    return no.hamre.booklist.app.model.Author(id = author.id, firstName = author.firstName, lastName = author.lastName)
  }

}