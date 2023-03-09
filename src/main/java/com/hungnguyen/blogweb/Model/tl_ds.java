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
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "ID_TL_DS")
public class tl_ds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_TL_DS;

    @ManyToOne
    @JoinColumn(name = "ID_TL",insertable = false,updatable = false)
    private the_loai theLoai;
    private int ID_TL;

//    @ManyToOne
//    @JoinColumn(name = "ID_SACH",insertable = false,updatable = false)
//    private dau_sach dauSach;
    private int ID_SACH;


}
