package com.tw;

import java.util.Map;

public class Student {
    private String sId;
    private String name;
    private float totalGrade;
    private float avgGrade;
    private Map<String, Float> courses;

    public Student(String sId, String name, Map<String, Float> courses, float totalGrade) {
        this.sId = sId;
        this.name = name;
        this.totalGrade = totalGrade;
        this.avgGrade = totalGrade/courses.size();
        this.courses = courses;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(float totalGrade) {
        this.totalGrade = totalGrade;
    }

    public float getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(float avgGrade) {
        this.avgGrade = avgGrade;
    }

    public Map<String, Float> getCourses() {
        return courses;
    }

    public void setCourses(Map<String, Float> courses) {
        this.courses = courses;
    }
}
