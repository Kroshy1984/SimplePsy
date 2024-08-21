package ru.sfedu.simplepsyspecialist.entity.nested;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectiveMethod {

    @Id
    private String id;
    private String name;
    private LocalDate date;
    private List<byte[]> images;

    public ProjectiveMethod() {
        this.date = LocalDate.now();
    }

    public ProjectiveMethod(String name) {
        this.name = name;
        this.date = LocalDate.now();
    }

    public ProjectiveMethod(String name, List<byte[]> images) {
        this.name = name;
        this.date = LocalDate.now();
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<byte[]> getImages() {
        return images;
    }

    public void setImages(List<byte[]> images) {
        this.images = images;
    }

    public void addImage(byte[] image) {
        if (images == null)
            images = new ArrayList<>();
        images.add(image);
    }
}
