package ru.sfedu.simplepsyspecialist.entity.nested;

public enum PreferMeetingFormat {
    ONLINE("Онлайн"),
    OFFLINE("Оффлайн");

    private String translation;

    PreferMeetingFormat(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
