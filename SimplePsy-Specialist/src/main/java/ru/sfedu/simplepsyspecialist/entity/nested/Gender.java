package ru.sfedu.simplepsyspecialist.entity.nested;

public enum Gender {
    MALE("Мужской"),
    FEMALE("Женский");

    private String translation;

    Gender(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
