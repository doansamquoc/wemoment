package com.doansamquoc.wemoment.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReactionType {
    LIKE("Like"), LOVE("Love"), HAHA("Haha"), WOW("Wow"), SAD("Sad"), ANGRY("Angry");

    private final String name;
}
