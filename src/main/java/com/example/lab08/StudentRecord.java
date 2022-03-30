package com.example.lab08;

public class StudentRecord {
    private String studentID;
    private float midterm;
    private float assignments;
    private float finalExam;
    private float finalMark;
    private String grade;

    public StudentRecord(String studentID, float assignments, float midterm, float finalExam) {
        super();
        this.studentID = studentID;
        this.assignments = assignments;
        this.midterm = midterm;
        this.finalExam = finalExam;

        this.finalMark = (float) ((0.2 * assignments) + (0.3 * midterm) + (0.5 * finalExam));

        if (80 <= finalMark && finalMark <= 100) {
            this.grade = "A";
        } else if (70 <= finalMark && finalMark <= 79) {
            this.grade = "B";
        } else if (60 <= finalMark && finalMark <= 69) {
            this.grade = "C";
        } else if (50 <= finalMark && finalMark <= 59) {
            this.grade = "D";
        } else {
            this.grade = "F";
        }

    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public float getMidterm() {
        return midterm;
    }

    public void setMidterm(float midterm) {
        this.midterm = midterm;
    }

    public float getAssignments() {
        return assignments;
    }

    public void setAssignments(float assignments) {
        this.assignments = assignments;
    }

    public float getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(float finalExam) {
        this.finalExam = finalExam;
    }

    public float getFinalMark() {
        return finalMark;
    }

    public void setFinalMark(float finalMark) {
        this.finalMark = finalMark;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}