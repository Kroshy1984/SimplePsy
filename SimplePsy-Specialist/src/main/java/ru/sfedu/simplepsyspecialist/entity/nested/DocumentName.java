package ru.sfedu.simplepsyspecialist.entity.nested;

public enum DocumentName {
    PASSPORT("Паспорт"),
    DRIVING_LICENSE("Водительское удостоверение");

    private String translation;

    DocumentName(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
