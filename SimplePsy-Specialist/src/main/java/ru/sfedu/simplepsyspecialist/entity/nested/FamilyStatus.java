package ru.sfedu.simplepsyspecialist.entity.nested;

public enum FamilyStatus {
    MARRIED("Женат/Замужем"),
    SINGLE("Холост/Не замужем"),
    DIVORCED("В разводе"),
    WIDOWED("Вдовец/вдова"),
    COHABITATION("Сожительство/Несостоявшийся брак"),
    MARRIAGE_WITH_SEPARATE_HOUSING("Официальный брак с раздельным проживанием"),
    LONELY("Одиночество/Не в постоянных отношениях"),
    FAMILY_WITH_CHILDREN("Семья с детьми");

    private String translation;

    FamilyStatus(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
