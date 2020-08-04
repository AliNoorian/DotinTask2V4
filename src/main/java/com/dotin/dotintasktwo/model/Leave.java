package com.dotin.dotintasktwo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_leave")
@Data
public class Leave extends Parent implements Serializable {


    @NotBlank(message = "Leave Subject is required")
    @Column(name = "c_leave_subject", nullable = false)
    private String leaveSubject;

    @NotBlank(message = "Leave Message is required")
    @Column(name = "c_leave_message", nullable = false)
    private String leaveMessage;

    @NotBlank(message = "is required")
    @Column(name = "c_leave_start")
    private String leaveFrom;

    @NotBlank(message = "is required")
    @Column(name = "c_leave_end")
    private String leaveTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_request_employee_id")
    private Employee employee;

 //   @Enumerated(EnumType.STRING)
 //   @Type(type="LeaveType")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "c_leave_type")
    private CategoryElement leaveType;

//    @Enumerated(EnumType.STRING)
//    @Type(type="LeaveStatus")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "c_leave_status")
    private CategoryElement leaveStatus;

}
