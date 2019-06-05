package com.endava.jiracollector.JiraCollector.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "board")
@EqualsAndHashCode
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "identifier", unique = true, nullable = false)
    private String identifier;

    @Column(name= "url", unique = true, nullable = false)
    private String url;

    @Column(name= "name", unique = true, nullable = false)
    private String name;

    @Column(name= "board_type", nullable = false)
    private String boardType;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "modification_date")
    private LocalDate modificationDate;

    @OneToMany(mappedBy="boardId")
    private List<Sprint> sprints;

    @PrePersist
    protected void onCreate() {
        this.registrationDate = LocalDate.now();
        this.modificationDate = LocalDate.now();
    }

}
