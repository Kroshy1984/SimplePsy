package ru.sfedu.simplepsyspecialist.entity.nested;

public enum PaymentType {
    CASH("Наличная"),

    CASHLESS("Безналичная");

    private String translation;

    PaymentType(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
