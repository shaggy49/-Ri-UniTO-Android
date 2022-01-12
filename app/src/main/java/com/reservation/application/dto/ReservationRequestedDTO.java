package com.reservation.application.dto;

public class ReservationRequestedDTO {
    private String course;
    private String teacher;
    private String date;
    private String time;

    public ReservationRequestedDTO(String course, String teacher, String date, String time) {
        this.course = course;
        this.teacher = teacher;
        this.date = date;
        this.time = time;
    }

    public String getDate() {
        return date;
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
