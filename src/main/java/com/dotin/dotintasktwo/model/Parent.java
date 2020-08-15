package com.dotin.dotintasktwo.model;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public class Parent  {

    @Id
    @Column(name = "c_id")
    @GeneratedValue
    private long id;

    @Version
    @Column(name = "c_version")
    private int version;

    @Column(name = "c_create_date")
    private String createDate;


    @Column(name = "c_modified_date")
    private String modifiedDate;

    @Column(name = "c_active")
    private Boolean active;



}
