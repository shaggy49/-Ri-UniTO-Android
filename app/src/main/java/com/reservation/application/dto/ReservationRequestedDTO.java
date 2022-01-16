package com.reservation.application.dto;

public class ReservationRequestedDTO {
    private String course;
    private String teacher;
    private String date;
    private String time;

    public ReservationRequestedDTO(String course, String teacher, String date, String time) {
        this.course = course;
        this.teacher = teacher;
//        this.date = date;
        switch(date) {
            case "lun":
                this.date = "Lunedì";
                break;
            case "mar":
                this.date = "Martedì";
                break;
            case "mer":
                this.date = "Mercoledì";
                break;
            case "gio":
                this.date = "Giovedì";
                break;
            case "ven":
                this.date = "Venerdì";
                break;
        }
        this.time = time + ":00";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCourse() {
        return course;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getTime() {
        return time;
    }
}
