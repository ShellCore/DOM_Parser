package com.shellcore.java.domparser;

/**
 * Created by Cesar. 07/06/2017.
 */
public class Student {

    public String id;
    public String active;
    public String firstName;
    public String lastName;
    public String location;

    public Student() {
    }

    @Override
    public String toString() {
        return firstName + " "
                + lastName + " "
                + "(" + id + ")" + " "
                + location + " "
                + "Active: " + active;
    }
}

