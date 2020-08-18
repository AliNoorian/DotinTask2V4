package com.dotin.dotintasktwo.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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

    @NotBlank(message = "متن پیام مورد نیاز است")
    @Column(name = "c_message")
    private String message;


    @Lob
    @Column(name = "c_attachment")
    private Blob attachment;

    @ManyToOne
    @JoinColumn(name = "c_sender_id")
    private Employee sender;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_receiver")
    @JoinColumn(name= "c_receiver_id")


    private List<Employee> receivers;
}
