package ru.sfedu.simplepsycustomer.simplepsyspecialist.simplepsyspecialist.specialist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Document("Calendar")
public class Calendar {
    private Session session;
    private String date;
    //pattern: "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$"
    private String timeOfMeeting;
    //private List<Client> clients;
    private TypeOfClients typeOfClients;

}
