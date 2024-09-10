package ru.sfedu.simplepsyspecialist.entity.nested;

public enum Sex {
    MALE("Мужской"),
    FEMALE("Женский");

    private String translation;

    Sex(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
