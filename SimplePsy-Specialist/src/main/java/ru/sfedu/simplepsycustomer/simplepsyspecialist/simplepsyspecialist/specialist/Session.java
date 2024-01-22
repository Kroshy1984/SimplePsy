package ru.sfedu.simplepsycustomer.simplepsyspecialist.simplepsyspecialist.specialist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Document("Session")
public class Session {
//    @Valid
//    private List<ClientEntry> clients;

    @NotBlank
    @Pattern(regexp = "^[а-яА-ЯёЁ]+\\s[0-9]{2}/[0-9]{2}/[0-9]{4}$")
    private String date;

    @NotBlank
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")
    private String time;

    @NotBlank
    @Size(min = 1, max = 255)
    private String typeOfClients;
}
