package guru.springframework.services;

import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipesServiceImplTest {

    RecipesServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipesServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();


    }

    @Test
    public void getRecipes() throws Exception {

        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() throws Exception {
        //given
        Long idToDelete = 2L;

        //when
        recipeRepository.deleteById(idToDelete);

        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }

    @Test(expected = NotFoundException.class)
    public void getRecipebyIdTestNotFound() throws Exception {
        Optional<Recipe> recipeOptional = Optional.empty();
        when(this.recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);
    }
}