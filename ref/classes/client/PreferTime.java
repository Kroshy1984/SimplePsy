package ru.sfedu.simplepsy.classes.client;

public class PreferTime{

    private PreferTimeType preferType;
    private String startTime;
    private String finishDate;

    public PreferTime(PreferTimeType preferType, String startTime, String finishDate){
        setPreferType(preferType);
        setStartTime(startTime);
        setFinishDate(finishDate);
    }

    public PreferTimeType getPreferType() {

        return preferType;
    }

    public void setPreferType(PreferTimeType preferType) {

        this.preferType = preferType;

    }

    public String getStartTime() {

        return startTime;
    }

    public void setStartTime(String startTime) {

        this.startTime = startTime;

    }

    public String getFinishDate() {

        return finishDate;
    }

    public void setFinishDate(String finishDate) {

        this.finishDate = finishDate;

    }

}