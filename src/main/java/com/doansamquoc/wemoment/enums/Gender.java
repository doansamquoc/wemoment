package com.doansamquoc.wemoment.enums;

public enum Gender {
    MALE("Male"), FEMALE("Female"), OTHER("Other");

    private final String displayName;

    Gender(String gender) {
        this.displayName = gender;
    }

    public String getDisplayName() {
        return displayName;
    }
}
