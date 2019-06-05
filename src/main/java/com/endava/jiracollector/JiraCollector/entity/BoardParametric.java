package com.endava.jiracollector.JiraCollector.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "boardParametric")
@EqualsAndHashCode
public class BoardParametric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "identifier", unique = true, nullable = false)
    private String identifier;

    @Column(name= "name", unique = true, nullable = false)
    private String name;

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
