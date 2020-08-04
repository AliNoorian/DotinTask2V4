package com.dotin.dotintasktwo.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_email")
@Data
public class Email extends Parent implements Serializable {

    @Column(name = "c_subject_mail")
    private String subject;


    @Column(name = "c_message")
    private String message;

    @Lob
    @Column(name = "c_attachment")
    private Blob attachment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_sender_id")
    private Employee sender;

    @OneToMany
    @JoinTable(name = "t_receiver",
            joinColumns = @JoinColumn(name = "c_employee_id"),
            inverseJoinColumns = @JoinColumn(name = "c_email_id"))
    private List<Employee> receivers;


}
