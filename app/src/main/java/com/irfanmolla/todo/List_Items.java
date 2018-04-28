package com.irfanmolla.todo;

/**
 * Created by user on 2/11/2018.
 */

public class List_Items {

    private String title,dayname,day,month,year,hour,minute,id,note;


    public List_Items( String id, String title, String dayname, String day, String month, String year, String hour, String minute, String note) {

        this.setId(id);
        this.settitle(title);
        this.setNote(note);
        this.setDayname(dayname);
        this.setDay(day);
        this.setMonth(month);
        this.setYear(year);
        this.setHour(hour);
        this.setMinute(minute);

    }

    public String getDayname() {
        return dayname;
    }

    public void setDayname(String dayname) {
        this.dayname = dayname;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getTitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
