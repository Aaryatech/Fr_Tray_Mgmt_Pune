package com.ats.patna_fr_tray_mgmt.bean;

/**
 * Created by MAXADMIN on 5/3/2018.
 */

public class InboxMessage {

    private int id;
    private String title;
    private String message;
    private String date;
    private int read;

    public InboxMessage() {
    }

    public InboxMessage(String title, String message, String date, int read) {
        this.title = title;
        this.message = message;
        this.date = date;
        this.read = read;
    }

    public InboxMessage(int id, String title, String message, String date, int read) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.date = date;
        this.read = read;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "InboxMessage{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                ", read=" + read +
                '}';
    }
}
