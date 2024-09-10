package ru.sfedu.simplepsyspecialist.entity.nested;

public enum Source {
    VIBER("Viber"),
    VK("ВК"),
    WHATSAPP("WhatsApp"),
    TELEGRAM("Telegram"),
    FACEBOOK("Facebook");

    private String translation;

    Source(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
