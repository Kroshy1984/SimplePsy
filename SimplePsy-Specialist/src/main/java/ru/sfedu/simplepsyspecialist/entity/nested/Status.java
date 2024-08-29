package ru.sfedu.simplepsyspecialist.entity.nested;

public enum Status {
    LEAD("Лид"),
    CUSTOMER("Заказчик");

    private String translation;

    Status(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
