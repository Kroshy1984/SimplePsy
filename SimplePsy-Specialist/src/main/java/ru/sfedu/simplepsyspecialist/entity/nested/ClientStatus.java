package ru.sfedu.simplepsyspecialist.entity.nested;

public enum ClientStatus {
    REGULAR("Регулярный"),
    ON_REQUEST("По запросу"),
    COMPLETED("Завершен"),
    APPLICATION("Заявка");

    private String translation;

    ClientStatus(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
