package com.endava.jiracollector.JiraCollector.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BoardDto {

    private String url;
    private String identifier;
    private String name;
    private String boardType;
    private LocalDate registrationDate;
    private LocalDate modificationDate;

}
