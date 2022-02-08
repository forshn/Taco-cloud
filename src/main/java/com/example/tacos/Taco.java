package com.example.tacos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Taco {
    private Long id;
    private Date createdAt;
    @NotNull
    @Size(min = 5, message = "Слишком короткое название")
    private String name;

    @Size(min = 1, message = "Должен быть минимум 1 ингредиент")
    private List<String> ingredients;
}
