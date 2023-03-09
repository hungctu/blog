package com.hungnguyen.blogweb.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class the_loai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_TL;
    private String TEN_TL;

    @OneToMany(targetEntity = tl_ds.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TL",referencedColumnName = "ID_TL")
    private List<tl_ds> tlDsList;

}
