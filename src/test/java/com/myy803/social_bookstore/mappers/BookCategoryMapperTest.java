package com.myy803.social_bookstore.mappers;

import com.myy803.social_bookstore.domain.models.BookCategory;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = "classpath:clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class BookCategoryMapperTest {

    @Autowired
    private BookCategoryMapper bookCategoryMapper;

    @Test
    @DisplayName("Find All Book Categories")
    public void should_find_all_book_categories() {
        BookCategory category1 = new BookCategory(1L, "Category 1");
        BookCategory category2 = new BookCategory(2L, "Category 2");
        bookCategoryMapper.save(category1);
        bookCategoryMapper.save(category2);

        List<BookCategory> categories = bookCategoryMapper.findAll();

        Assertions.assertEquals(2, categories.size());
        Assertions.assertEquals(category1.getId(), categories.get(0).getId());
        Assertions.assertEquals(category2.getId(), categories.get(1).getId());
        Assertions.assertEquals(category1.getCategory(), categories.get(0).getCategory());
        Assertions.assertEquals(category2.getCategory(), categories.get(1).getCategory());
    }
}
