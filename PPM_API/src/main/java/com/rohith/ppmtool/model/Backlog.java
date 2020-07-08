package com.rohith.ppmtool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Backlog {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    private int PTSequence = 0;

    private String projectIdentifier;

    // One To One with Project
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id",nullable = false)
    @JsonIgnore
    private Project project;

    // One to Many Project Task
    @OneToMany(cascade = CascadeType.REFRESH ,fetch = FetchType.EAGER ,mappedBy = "backlog",orphanRemoval = true)
    private List<ProjectTask> projectTaskList = new ArrayList<>();

}
