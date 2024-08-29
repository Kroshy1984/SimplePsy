package ru.sfedu.simplepsyspecialist.entity.nested;

public enum Day {
    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье");

    private String translation;

    Day(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
