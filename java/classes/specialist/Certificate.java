package classes.specialist;

import java.time.LocalDate;

public class Certificate {

    private final String number; //регистрационный номер
    private final LocalDate date; //дата вручения
    private final String theme; //тема

    public Certificate(String number, LocalDate date, String theme) {
        this.number = number;
        this.date = date;
        this.theme = theme;
    }

    private String getNumber() {
        return number;
    }

    private LocalDate getDate() {
        return date;
    }

    private String getTheme() {
        return theme;
    }
}
