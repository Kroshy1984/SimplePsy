package classes;
import java.time.LocalDate;
import java.time.LocalTime;
public class Meeting {

    private LocalDate date;
    private LocalTime time;
    private LocalTime duration;
    private String format;
    private String platform;
    private String address;
    private int price;

    public Meeting(LocalDate date, LocalTime time, LocalTime duration, String format, String platform, String address, int price){
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.price = price;
        this.format = format;
        if(this.format.equals("online"))
            setPlatform(platform);
        if(this.format.equals("offline"))
            setAddress(address);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
