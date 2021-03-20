package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        log.debug("IndexController::constructor");
        this.recipeService = recipeService;
    }

    @GetMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        log.debug("IndexController::getIndexPage");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
