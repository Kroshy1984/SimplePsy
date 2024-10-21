package ru.sfedu.simplepsyspecialist.entity.nested;

public enum TypeOfClient {
    ADULT("Взрослый"),
    CHILD("Ребенок"),
    COUPLE("Пара"),
    HOLIDAY("Отпуск");
    private String translation;

    TypeOfClient(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
