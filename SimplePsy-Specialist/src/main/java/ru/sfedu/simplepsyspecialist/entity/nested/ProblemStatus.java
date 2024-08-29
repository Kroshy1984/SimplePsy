package ru.sfedu.simplepsyspecialist.entity.nested;

public enum ProblemStatus {
    NEW("Новая"),
    APPROVED("Подтверждена"),
    DECLINED("Отклонена"),
    DEPRECATED("Устарела");

    private String translation;

    ProblemStatus(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
