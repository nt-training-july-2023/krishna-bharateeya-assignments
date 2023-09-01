package com.nucleusteq.assessmentPlatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing user registration information.
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "userSeq",
initialValue = Registration.ID_INITIAL_VALUE, allocationSize = 1)
public class Registration {

    /**
     * Constant for initial value of Registration ID sequence.
     */
    public static final int ID_INITIAL_VALUE = 1010;

    /**
     * The ID of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    private int userId;

    /**
     * The first name of the user.
     */
    @Column(nullable = false)
    private String firstName;

    /**
     * The last name of the user.
     */
    @Column(nullable = false)
    private String lastName;

    /**
     * The mobile number of the user.
     */
    @Column(nullable = false, unique = true)
    private String mobileNumber;

    /**
     * The role of the user.
     */
    @Column(nullable = false)
    private String userRole;

    /**
     * The email address of the user.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * The password of the user.
     */
    @Column(nullable = false)
    private String password;

}
