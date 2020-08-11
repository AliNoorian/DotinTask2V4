package com.dotin.dotintasktwo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_employee")
@Data
public class Employee extends Parent implements Serializable {


    @NotBlank(message = "is required")
    @Column(name = "c_first_name")
    private String firstName;

    @NotBlank(message = "is required")
    @Column(name = "c_last_name")
    private String lastName;

    @Column(name = "c_gender")
    private String employeeGender;

    @Column(name = "c_email")
    @javax.validation.constraints.Email(message = "Please provide a valid email!")
    private String email;

    @ManyToOne
    @JoinColumn(name = "c_manager_id")
    private Employee manager;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Employee> managerList;


    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Leave> leaves;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "c_role_id")
    private CategoryElement employeeRole;


    @ManyToOne(fetch=FetchType.EAGER)
    @JoinTable(name = "t_receiver")
    private Employee receiver;
}











