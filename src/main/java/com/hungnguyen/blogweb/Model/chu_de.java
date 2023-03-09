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
public class chu_de {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_CD;
    private String TEN_CD;

    @OneToMany(targetEntity = cd_ds.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CD",referencedColumnName = "ID_CD")
    private List<cd_ds> cdDs;
}
