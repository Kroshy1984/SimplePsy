package ru.sfedu.simplepsyspecialist.entity.nested;

public enum PreferredMeetingFormat {
    ONLINE("Онлайн"),
    OFFLINE("Оффлайн");

    private String translation;

    PreferredMeetingFormat(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
