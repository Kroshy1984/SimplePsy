package specialist;

import java.time.LocalDate;

public class Certificate {

    private final String number; //регистрационный номер
    private final LocalDate date; //дата вручения
    private final String theme; //тема

    public Certificate(String number, LocalDate date, String specialization) {
        this.number = number;
        this.date = date;
        this.theme = specialization;
    }

    private String getNumber() {
        return number;
    }

    private LocalDate getDate() {
        return date;
    }

    private String getSpecialization() {
        return theme;
    }
}
