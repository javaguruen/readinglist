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
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "book_id")
    val tags: List<Tag> = emptyList(),
*/
    val readingOrder: Int = 0,
    val medium: Medium? = null
){
    override fun hashCode(): Int {
        return 1001;
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
){
    override fun hashCode(): Int {
        return 1;
    }
}

@Entity
data class Tagging(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    val name: Tag,

    @ManyToOne
    val user: User,

    val tagged: LocalDateTime = LocalDateTime.now()
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
    val deleted: LocalDateTime? = null
)

enum class Medium { PAPIR, EBOK, LYDBOK }