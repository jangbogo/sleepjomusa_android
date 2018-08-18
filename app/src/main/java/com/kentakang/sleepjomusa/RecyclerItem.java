package com.kentakang.sleepjomusa;

public class RecyclerItem {
    private int image;
    private String title;
    private String link;

    int getImage() {
        return this.image;
    }

    String getTitle() {
        return this.title;
    }

    String getLink() {
        return this.link;
    }

    RecyclerItem (int image, String title, String link) {
        this.image = image;
        this.title = title;
        this.link = link;
    }
}
