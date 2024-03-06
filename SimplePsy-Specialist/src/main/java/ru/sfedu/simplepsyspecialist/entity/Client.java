package ru.sfedu.simplepsyspecialist.entity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("Client")
public class Client {

    @NotBlank
    @Length(max = 255)
    @Pattern(regexp = "^[А-я]+^[а-я]$")
    private String id;

    @NotBlank
    @Length(max = 255)
    private String status;

    @NotBlank
    @Length(max = 255)
    @Pattern(regexp = "^[А-я]+^[а-я]$")
    private String name;

    @NotBlank
    @Length(max = 255)
    @Pattern(regexp = "^[А-Я]+^[а-я]$")
    private String surname;

    @NotBlank
    @Length(max = 255)
    @Pattern(regexp = "^[А-Я]+^[а-я]$")
    private String middleName;

    @Valid
    private Contact contact;

    @NotBlank
    @Length(max = 255)
    @Pattern(regexp = "^(Viber|vk|WhatsApp|Telegramm|facebook)$")
    private String source;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Contact {

        @Length(max = 12)
        private String phone;

        @Length(max = 320)
        private String email;


        @Length(max = 320)
        private String tg;

        // Геттеры и сеттеры
    }
}
