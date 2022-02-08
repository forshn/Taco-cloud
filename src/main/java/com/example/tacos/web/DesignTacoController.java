package com.example.tacos.web;

import com.example.tacos.Ingredient;
import com.example.tacos.Ingredient.Type;
import com.example.tacos.Taco;
import com.example.tacos.data.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    private final IngredientRepository repository;

    @Autowired
    public DesignTacoController(IngredientRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        repository.findAll().forEach(ingredients::add);

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";

        /*
        •	Большинство методов обработки запросов завершаются возвращением логического имени
        представления, например шаблона Thymeleaf, на который передается запрос
         (вместе с любыми данными модели).
         */
    }


    /*
    @Valid аннотация говорит Spring MVC, чтобы выполнить проверку на представленном объекте Taco
    после того, как он привязан к представленным данным формы и перед вызовом
    метода processDesign(). Если есть какие-либо ошибки проверки,
    сведения об этих ошибках будут записаны в объект Errors, который передается в processDesign().
     */

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors) {
        // Save the taco design…
        // We'll do this in chapter 3

        if (errors.hasErrors()) {
            return "design";
        }
        log.info("Processing design: " + design);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(a -> a.getType().name().equals(type.name()))
                .collect(Collectors.toList());
    }
}


