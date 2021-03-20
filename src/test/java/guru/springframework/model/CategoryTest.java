package guru.springframework.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() {
        category = new Category();

    }

    @Test
    public void getId() {
        category.setId(4L);
        assertEquals(4l, category.getId().longValue());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}