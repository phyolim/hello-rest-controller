package com.mckesson.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class Person {
    private String name;
    @JsonFormat(pattern="MM/dd/yyyy")
    private Date birthDate;
    private String email;

    public Person(){
    }

    public Person(String name, Date birthDate, String email) {
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date date){
        this.birthDate = date;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public int getAge() {
        Calendar c = Calendar.getInstance();
        c.setTime(this.birthDate);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DATE);
        LocalDate ll = LocalDate.of(year, month, day);
        LocalDate now = LocalDate.now();
        Period diff = Period.between(ll, now);
        return diff.getYears();
    }
}
