package no.hamre.booklist.app.rest.api

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

data class Book(
    val id: Long? = null,
    val authors: List<Author>,
    @ApiModelProperty(required = true)
    val originalTitle: String,
    val norwegianTitle: String? = null,
    val language: String? = null,
    val tags: Set<Tag> = emptySet(),
    val readingOrder: Int = 0,
    val medium: Medium? = null,
    val link: String? = null,
    @ApiModelProperty(required = true)
    val stat: Status = Status.ON_LIST
)
@ApiModel(description = "UNREAD: Not read yet")
enum class Status {
    @ApiModelProperty("Not read yet", example = "UNREAD: not read") UNREAD,
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