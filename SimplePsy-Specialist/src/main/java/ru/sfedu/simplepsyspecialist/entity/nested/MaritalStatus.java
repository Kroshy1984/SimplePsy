package ru.sfedu.simplepsyspecialist.entity.nested;

public enum MaritalStatus {
    SINGLE("Холост/Не замужем"),
    MARRIED("Женат/Замужем");

    private String translation;

    MaritalStatus(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
