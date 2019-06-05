package com.endava.jiracollector.JiraCollector.dto;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class IssueDto {

    private String name;
    private String identifier;
    private String assignee;
    private String url;
    private String boardType;
    private LocalDate registrationDate;
    private LocalDate modificationDate;

}
