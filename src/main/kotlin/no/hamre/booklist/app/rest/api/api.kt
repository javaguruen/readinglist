package no.hamre.booklist.app.rest.api

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(
    requiredProperties = ["stat"],
    implementation = Book::class
)
data class Book(
    val id: Long? = null,
    @field:Schema(required = true)
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
@Schema(description = "UNREAD: Not read yet")
enum class Status {
    @field:Schema(description = "Not read yet", example = "UNREAD: not read") UNREAD,
    READ,
    ON_LIST, WONT_READ }
data class Author(val id: Long? = null, val firstName: String, val lastName: String,
                  val ustat: Status = Status.UNREAD)
data class Tag(val name: TagName, val tagged: LocalDateTime = LocalDateTime.now())
data class TagName(val name: String, val deleted: LocalDateTime? = null)
enum class Medium { PAPIR, EBOK, LYDBOK }
data class User(
    val id: Long?,
    val email: String
)