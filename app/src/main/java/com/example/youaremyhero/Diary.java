package com.example.youaremyhero;

public class Diary {
    int id;

    String babyContents;
    String parentContents;
    String newOne;
    String theme;

    String createDateStr;
    String mood;

    String place;

    public Diary(int id, String place, String parentContents, String mood, String babyContents, String newOne, String theme, String createDateStr){
        this.id = id;
        this.babyContents = babyContents;
        this.parentContents = parentContents;
        this.newOne = newOne;
        this.theme = theme;
        this.createDateStr = createDateStr;
        this.mood = mood;
        this.place = place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public int getId() {
        return id;
    }



    public void setBabyContents(String babyContents) {
        this.babyContents = babyContents;
    }

    public void setParentContents(String parentContents) {
        this.parentContents = parentContents;
    }

    public void setNewOne(String newOne) {
        this.newOne = newOne;
    }

    public String getBabyContents() {
        return babyContents;
    }

    public String getParentContents() {
        return parentContents;
    }

    public String getNewOne() {
        return newOne;
    }

    public String getTheme() {
        return theme;
    }



    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setId(int id) {
        this.id = id;
    }





    public void setTheme(String theme) {
        this.theme = theme;
    }



    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }
}
