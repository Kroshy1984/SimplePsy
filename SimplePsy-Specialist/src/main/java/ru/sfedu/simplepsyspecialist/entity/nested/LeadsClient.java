package ru.sfedu.simplepsyspecialist.entity.nested;

public enum LeadsClient {
    PARENT("Родитель"),
    BOTH_PARENTS("Оба родителя"),
    GUARDIAN("Попечитель");

    private String translation;

    LeadsClient(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
