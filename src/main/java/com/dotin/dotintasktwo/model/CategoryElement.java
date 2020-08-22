package com.dotin.dotintasktwo.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_category_element")
@ToString(exclude = {"category"})
@Data
public class CategoryElement extends Parent implements Serializable {

    @Column(name = "c_name")
    private String name;

    @Column(name = "c_code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "c_category_id")
    private Category category;



}
