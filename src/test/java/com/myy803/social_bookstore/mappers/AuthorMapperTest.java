package com.myy803.social_bookstore.mappers;

import com.myy803.social_bookstore.domain.models.Author;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = "classpath:clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AuthorMapperTest {

    @Autowired
    private AuthorMapper authorMapper;

    @Test
    @DisplayName("Find All Authors")
    public void should_find_all_authors() {
        Author author1 = new Author(1L, "Author 1");
        Author author2 = new Author(2L, "Author 2");
        authorMapper.save(author1);
        authorMapper.save(author2);

        List<Author> authors = authorMapper.findAll();

        Assertions.assertEquals(2, authors.size());
        Assertions.assertEquals(author1.getId(), authors.get(0).getId());
        Assertions.assertEquals(author2.getId(), authors.get(1).getId());
        Assertions.assertEquals(author1.getName(), authors.get(0).getName());
        Assertions.assertEquals(author2.getName(), authors.get(1).getName());
    }
}
