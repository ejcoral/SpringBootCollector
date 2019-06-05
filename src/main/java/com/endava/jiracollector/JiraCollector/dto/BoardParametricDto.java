package com.endava.jiracollector.JiraCollector.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BoardParametricDto {

    private String identifier;
    private String name;
    private LocalDate registrationDate;
    private LocalDate modificationDate;

}
