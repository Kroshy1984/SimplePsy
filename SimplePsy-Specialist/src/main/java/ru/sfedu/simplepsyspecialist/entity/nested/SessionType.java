package ru.sfedu.simplepsyspecialist.entity.nested;

public enum SessionType {
    CLIENT("Клиент"),

    ANOTHER("Другое");

    private String translation;

    SessionType(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
