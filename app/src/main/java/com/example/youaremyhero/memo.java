package com.example.youaremyhero;

public class memo {

    String theme;
    String memoContent;
    int id;


    public memo(int id,  String theme, String memoContent){
        this.id = id;
        this.theme = theme;
        this.memoContent = memoContent;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getMemoContent() {
        return memoContent;
    }

    public void setMemoContent(String memoContent) {
        this.memoContent = memoContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
