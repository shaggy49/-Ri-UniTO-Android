package com.reservation.application.dto;

public class ReservationAvailableDTO {
    private int id;
    private String course;
    private String teacher;
    private String time;

    public ReservationAvailableDTO(int id, String course, String teacher, String time) {
        this.id = id;
        this.course = course;
        this.teacher = teacher;
        this.time = time;
    }

    public int getId() {
        return id;
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
