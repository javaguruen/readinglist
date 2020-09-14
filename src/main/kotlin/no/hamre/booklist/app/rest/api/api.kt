package no.hamre.booklist.app.rest.api

import java.time.LocalDateTime

data class Book(
    val id: Long? = null,
    val authors: List<Author>,
    val originalTitle: String,
    val norwegianTitle: String? = null,
    val language: String? = null,
    val tags: Set<Tag> = emptySet(),
    val readingOrder: Int = 0,
    val medium: Medium? = null,
    val link: String? = null,
    val stat: Status = Status.ON_LIST
)

enum class Status {
  UNREAD,
  READ,
  ON_LIST, WONT_READ
}

data class Author(val id: Long? = null, val firstName: String, val lastName: String)

data class Tag(val name: TagName, val tagged: LocalDateTime = LocalDateTime.now())
data class TagName(val name: String, val deleted: LocalDateTime? = null)
enum class Medium { PAPIR, EBOK, LYDBOK }
data class User(
    val id: Long?,
    val email: String
)