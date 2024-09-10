package ru.sfedu.simplepsyspecialist.entity.nested;

public enum SessionFormat {
    ONLINE("Онлайн"),

    OFFLINE("Оффлайн");

    private String translation;

    SessionFormat(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
