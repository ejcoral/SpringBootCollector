package com.endava.jiracollector.JiraCollector.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "issue")
@EqualsAndHashCode
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "name", unique = true, nullable = false)
    private String name;

    @Column(name= "identifier", unique = true, nullable = false)
    private String identifier;

    @Column(name= "assignee")
    private String assignee;

    @Column(name= "url", unique = true, nullable = false)
    private String url;

    @Column(name= "board_type", nullable = false)
    private String boardType;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "modification_date")
    private LocalDate modificationDate;

    @PrePersist
    protected void onCreate() {
        this.registrationDate = LocalDate.now();
        this.modificationDate = LocalDate.now();
    }

}
