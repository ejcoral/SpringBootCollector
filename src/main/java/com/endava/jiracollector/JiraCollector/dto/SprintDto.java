package com.endava.jiracollector.JiraCollector.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SprintDto {

    private String identifier;
    private String name;
    private String startDate;
    private String endDate;
    private String url;
    private Boolean sprintStatus;
    private String boardType;
    private LocalDate registrationDate;
    private LocalDate modificationDate;
}
