package com.wgu.edu.request;


public class CGStudent {
    private int PIDM;
    private String firstName;
    private String lastName;

    public int getPIDM() {
        return PIDM;
    }

    public void setPIDM(int PIDM) {
        this.PIDM = PIDM;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "CGStudent{" +
                "PIDM=" + PIDM +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}



