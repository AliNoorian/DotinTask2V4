package com.dotin.dotintasktwo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;



@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_leave")
@Data
public class Leave extends Parent implements Serializable {


    @NotBlank(message = "عنوان مرخصی الزامی می باشد")
    @Column(name = "c_leave_subject", nullable = false)
    private String leaveSubject;

    @NotBlank(message = "شرح مرخصی الزامی می باشد")
    @Column(name = "c_leave_message", nullable = false)
    private String leaveMessage;

    @NotBlank(message = "تاریخ شروع را مشخص کنید")
    @Column(name = "c_leave_start")
    private String leaveFrom;

    @NotBlank(message = "تاریخ پایان را مشخص کنید")
    @Column(name = "c_leave_end")
    private String leaveTo;

    //@NotEmpty(message = "کارمند را مشخص کنید")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "c_request_employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "c_leave_status")
    private CategoryElement leaveStatus;


}
