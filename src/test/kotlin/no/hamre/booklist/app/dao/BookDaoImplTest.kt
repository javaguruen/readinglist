package no.hamre.booklist.app.dao

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import javax.persistence.EntityManager
import javax.sql.DataSource


@DataJpaTest
internal class BookDaoImplTest(
  @Autowired val dataSource: DataSource,
  @Autowired val jdbcTemplate: JdbcTemplate,
  @Autowired val entityManager: EntityManager,
  @Autowired val tagRepository: TagRepository){

  @Test
  fun injectedComponentsAreNotNull() {
    assertNotNull(dataSource)
    assertNotNull(jdbcTemplate)
    assertNotNull(entityManager)
    assertNotNull(tagRepository)
  }

  @Test
  internal fun findById() {
    val boughtTag = tagRepository.findById("BOUGHT")
    assertNotNull(boughtTag)

  }
}