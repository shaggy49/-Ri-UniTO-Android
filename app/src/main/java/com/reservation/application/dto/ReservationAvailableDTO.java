package com.reservation.application.dto;

public class ReservationAvailableDTO {
    private String course;
    private String teacher;
    private String time;

    public ReservationAvailableDTO(String course, String teacher, String time) {
        this.course = course;
        this.teacher = teacher;
        this.time = time;
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
