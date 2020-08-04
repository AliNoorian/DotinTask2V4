package com.dotin.dotintasktwo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
public class Parent implements Serializable {

    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private long id;

    @Column(name = "c_version")
    private int version;

    @Column(name = "c_create_date")
    private String createDate;

    @Basic
    @Column(name = "c_modified_date")
    private String modifiedDate;

    @Column(name = "c_active")
    private Boolean active;



}
