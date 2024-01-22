package ru.sfedu.simplepsycustomer.simplepsyspecialist.simplepsyspecialist.specialist;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document("Specialist")
public class Specialist {

    @Id
    private String id;

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

    @DBRef
    @Valid
    private List<Calendar> calendar;

    private String notification;
    private String password;

//    @Valid
//    private List<ClientEntry> clients;


    public Specialist(String id, String name, String surname, String middleName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
    }
}
