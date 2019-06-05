package com.endava.jiracollector.JiraCollector.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "sprint")
@EqualsAndHashCode
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "identifier", unique = true, nullable = false)
    private String identifier;

    @Column(name= "name", unique = true, nullable = false)
    private String name;

    @Column(name= "start_date", nullable = false)
    private String startDate;

    @Column(name= "end_date", nullable = false)
    private String endDate;

    @Column(name= "url", unique = true, nullable = false)
    private String url;

    @Column(name="sprint_status", nullable = false)
    private Boolean sprintStatus;

    @Column(name="board_type",  nullable = false)
    private String boardType;

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board boardId;

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
