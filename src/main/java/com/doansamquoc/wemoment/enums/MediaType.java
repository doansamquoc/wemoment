package com.doansamquoc.wemoment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MediaType {
    IMAGE("image"), VIDEO("Video");

    private final String name;
}
