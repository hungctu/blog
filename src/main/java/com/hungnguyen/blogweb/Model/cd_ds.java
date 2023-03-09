package com.hungnguyen.blogweb.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "ID_CD_DS")
public class cd_ds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_CD_DS;

    @ManyToOne
    @JoinColumn(name = "ID_CD",insertable = false,updatable = false)
    private chu_de chuDe;
    private int ID_CD;

//    @ManyToOne
//    @JoinColumn(name = "ID_SACH",insertable = false,updatable = false)
//    private dau_sach dauSach;
    private int ID_SACH;
}
