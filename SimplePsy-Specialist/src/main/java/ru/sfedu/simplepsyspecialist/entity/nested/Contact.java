package ru.sfedu.simplepsyspecialist.entity.nested;

public class Contact {
    private String phone;

    private String email;

    private String tg;

    public Contact(String phone, String email, String tg) {
        this.phone = phone;
        this.email = email;
        this.tg = tg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTg() {
        return tg;
    }

    public void setTg(String tg) {
        this.tg = tg;
    }
}
