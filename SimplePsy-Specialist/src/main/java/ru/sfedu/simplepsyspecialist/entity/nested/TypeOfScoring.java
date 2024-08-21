package ru.sfedu.simplepsyspecialist.entity.nested;

public enum TypeOfScoring {
    QUESTIONER("Опрос"),
    TEST("Тест");

    private String translation;

    TypeOfScoring(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}