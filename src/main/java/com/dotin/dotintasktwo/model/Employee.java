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


    @NotBlank(message = "این قسمت مورد نیاز است")
    @Column(name = "c_first_name")
    private String firstName;

    @NotBlank(message = "این قسمت مورد نیاز است")
    @Column(name = "c_last_name")
    private String lastName;

    @NotBlank(message = "این قسمت مورد نیاز است")
    @Column(name = "c_gender")
    private String employeeGender;

    @NotBlank(message = "این قسمت مورد نیاز است")
    @Column(name = "c_email")
    @javax.validation.constraints.Email(message = "لطفاً ایمیل را اصلاح کنید!")
    private String email;

    @ManyToOne
    @JoinColumn(name = "c_manager_id")
    private Employee manager;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "c_role_id")
    private CategoryElement employeeRole;


}











