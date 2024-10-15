package ru.sfedu.simplepsyspecialist.entity.nested;

import java.time.LocalDate;

public class Image {
    byte[] image;
    private LocalDate date;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
