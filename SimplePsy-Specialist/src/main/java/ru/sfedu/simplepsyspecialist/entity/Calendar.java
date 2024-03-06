package ru.sfedu.simplepsyspecialist.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Document("Calendar")
public class Calendar {
    private List<Session> sessions;

}
