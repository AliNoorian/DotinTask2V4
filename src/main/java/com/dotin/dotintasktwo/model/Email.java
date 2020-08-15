package com.dotin.dotintasktwo.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Blob;
import java.util.List;


@Entity
@Table(name = "t_email")
@Data
public class Email extends Parent implements Serializable {

    @Column(name = "c_subject_mail")
    private String subject;

    @NotBlank(message = "متن پیام مورد نیاز است")
    @Column(name = "c_message")
    private String message;

    @Column(name = "c_file_name")
    private String fileName;

    @Column(name = "c_file_type")
    private String fileType;

    @Lob
    @Column(name = "c_attachment")
    private byte[] attachment;

    @ManyToOne
    @JoinColumn(name = "c_sender_id")
    private Employee sender;

    @NotEmpty(message = "گیرنده را مشخصی نمایید")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_receiver")
    @JoinColumn(name= "c_receiver_id")
    private List<Employee> receivers;


}
