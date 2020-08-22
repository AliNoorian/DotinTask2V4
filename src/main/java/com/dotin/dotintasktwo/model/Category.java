package com.dotin.dotintasktwo.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;



@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_category")
@Setter
@Getter
public class Category extends Parent implements Serializable  {


    @Column(name = "c_category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CategoryElement> categoryElements;


}
