package no.hamre.booklist.app.model

import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.FetchType


@Entity
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val originalTitle: String,

    val norwegianTitle: String? = null,

    @ManyToMany(fetch = FetchType.LAZY,
        cascade = [
          CascadeType.PERSIST,
          CascadeType.MERGE
        ])
    @JoinTable(name = "book_tag",
        joinColumns = [JoinColumn(name = "book_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_name")])
    val tags: Set<Tag> = hashSetOf(),


    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST])
/*
    @JoinTable(
        name = "author_book",
        joinColumns = [JoinColumn(name = "book_id")],
        inverseJoinColumns = [JoinColumn(name = "author_id")])
*/
    val authors: Set<Author> = emptySet(),

    val language: String? = null,


/*
    @ManyToMany( cascade = [CascadeType.ALL] )
    @JoinTable(
        name = "book_tag",
        joinColumns = [ JoinColumn(name = "book_id") ],
        inverseJoinColumns = [ JoinColumn(name = "tag_id") ]
    )
    val tags: Set<Tag> = hashSetOf(),
*/
//    val tags: List<Tag> = emptyList(),
    val readingOrder: Int = 0,
    val medium: Medium? = null
) {
  override fun hashCode(): Int {
    return 1001
  }
}


@Entity
data class Author(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val firstName: String,

    @Column(nullable = false)
    val lastName: String,

    @ManyToMany(mappedBy = "authors")
    val books: Set<Book> = emptySet()
) {
  override fun hashCode(): Int {
    return 1
  }
}

@Entity(name = "book_tag")
data class Tagging(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    val tag: Tag,

/*
    @ManyToOne
    val user: User,

*/
    val tagged: LocalDateTime = LocalDateTime.now(),

    val deleted: LocalDateTime? = LocalDateTime.now()
)

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val created: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    val updated: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    val hashedPassword: String,

    val deleted: LocalDateTime?
)

@Entity
data class Tag(
    @Id
    val name: String,

    @ManyToMany(mappedBy = "tags")
    val books: Set<Book>

)

enum class Medium { PAPIR, EBOK, LYDBOK }