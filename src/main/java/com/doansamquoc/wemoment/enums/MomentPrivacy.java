package com.doansamquoc.wemoment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MomentPrivacy {

    PRIVATE("Private"),
    PUBLIC("Public"),
    FRIEND_ONLY("Friend only"),
    SPECIFIC_FRIEND("Specific friends");

    private final String name;
}
