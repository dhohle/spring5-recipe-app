package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.model.Category;
import guru.springframework.model.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final NotesToNotesCommand notesConverter;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConverter, IngredientToIngredientCommand ingredientConverter, NotesToNotesCommand notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Override
    public RecipeCommand convert(Recipe source) {
        if(source == null)
            return null;
        final RecipeCommand command = new RecipeCommand();
        command.setId(source.getId());
        command.setCookTime(source.getCookTime());
        command.setPrepTime(source.getPrepTime());
        command.setDescription(source.getDescription());
        command.setDifficulty(source.getDifficulty());
        command.setDirections(source.getDirections());
        command.setImage(source.getImage());
        command.setServings(source.getServings());
        command.setSource(source.getSource());
        command.setUrl(source.getUrl());
        command.setNotes(notesConverter.convert(source.getNotes()));

        if(source.getCategories()!=null)
            source.getCategories().forEach((Category category) -> command.getCategories().add(categoryConverter.convert(category)));
        if(source.getIngredients()!=null)
            source.getIngredients().forEach(ingredient -> command.getIngredients().add(ingredientConverter.convert(ingredient)));

        return command;
    }
}
