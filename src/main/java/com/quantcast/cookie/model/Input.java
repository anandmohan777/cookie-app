package com.quantcast.cookie.model;

import java.time.LocalDate;

public class Input {
    private String fileName;
    private LocalDate date;

    public Input(String fileName, LocalDate date) {
        this.fileName = fileName;
        this.date = date;
    }

    public Input() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
