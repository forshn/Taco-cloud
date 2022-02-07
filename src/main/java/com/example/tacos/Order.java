package com.example.tacos;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class Order {
    @NotBlank(message = "Должно быть имя")
    private String name;
    @NotBlank(message = "Должно быть название улицы")
    private String street;
    @NotBlank(message = "Должно быть название города")
    private String city;
    @NotBlank(message = "Должно быть Штата")
    private String state;
    @NotBlank(message = "Должен быть индекс")
    private String zip;
    @CreditCardNumber(message = "Некорректный номер карты")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;
}
