package ru.sfedu.simplepsyspecialist.entity.nested;

public enum TypeOfTreatment {
    MESSENGER("Мессенджер"),
    SOCIAL_NETWORK("Социальная сеть"),
    AGGREGATOR("Агрегатор");

    private String translation;

    TypeOfTreatment(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
