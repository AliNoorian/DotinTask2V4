package com.dotin.dotintasktwo.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "t_category")
@Data
public class Category extends Parent  {


//    @Enumerated(EnumType.STRING)
    @Column(name = "c_category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CategoryElement> categoryElements;


}
