package ru.sfedu.simplepsyspecialist.entity.nested;

public enum OnlineMeetingPlace {
    ZOOM("Zoom"),
    TELEMOST("Телемост"),
    GOOGLE_MEET("GoogleMeet"),
    TELEGRAM("Telegram"),
    WHATSAPP("WhatsApp"),
    SKYPE("Skype");

    private String translation;

    OnlineMeetingPlace(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
