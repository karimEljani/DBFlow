package com.ibizabroker.lms.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmailRequest {

    private String to;
    private String subject;
    private String content;

    // Constructors, getters, and setters

}
