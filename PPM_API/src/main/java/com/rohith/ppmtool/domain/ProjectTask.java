package com.rohith.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProjectTask {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(updatable = false,unique = true)
    private String projectSequence;

    @NotBlank(message =  "Please include a project summary")
    private String summary;

    private String acceptanceCriteria;

    private String status;

    private Integer priority;

    private Date dueDate;

    //Many To One with Backlog

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "backlog_id",updatable = false,nullable = false)
    @JsonIgnore
    private Backlog backlog;

    @Column (updatable = false)
    private String projectIdentifier;

    private Date createdAt;

    private Date updatedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

    @PreUpdate
    protected  void onUpdate(){
        this.updatedAt = new Date();
    }

}
