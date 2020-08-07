package com.dotin.dotintasktwo.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_category_element")
@Data
public class CategoryElement extends Parent implements Serializable {


    @Column(name = "c_english_name")
    private String englishTypeName;

    @Column(name = "c_persian_name")
    private String persianTypeName;

    @NotBlank(message = "is required")
    @Column(name = "c_staff_id")
    private int staffId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "c_category_name")
    private EmployeeRole employeeRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "c_category_name")
    private LeaveType leaveType;

    @Enumerated(EnumType.STRING)
    @Column(name = "c_category_name")
    private LeaveStatus leaveStatus;


}
