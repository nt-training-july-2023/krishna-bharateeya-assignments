package com.nucleusteq.assessmentPlatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "quizSeq", initialValue = 4010, allocationSize = 1)
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quizSeq")
    private int quizId;

    @Column(nullable = false)
    private int quizName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}