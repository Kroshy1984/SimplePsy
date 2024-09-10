package ru.sfedu.simplepsyspecialist.entity.nested;

public enum PriorityCommunicationChannel {
    TELEGRAM("Telegram"),
    WHATSAPP("WhatsApp"),
    VIBER("Viber"),
    SKYPE("Skype"),
    EMAIL("Почта"),
    SNAPCHAT("Snapchat");

    private String translation;

    PriorityCommunicationChannel(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
