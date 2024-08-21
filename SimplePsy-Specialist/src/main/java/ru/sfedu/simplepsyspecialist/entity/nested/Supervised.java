package ru.sfedu.simplepsyspecialist.entity.nested;

public enum Supervised {
    YES("Да"),
    NO("Нет");

    private String translation;

    Supervised(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
