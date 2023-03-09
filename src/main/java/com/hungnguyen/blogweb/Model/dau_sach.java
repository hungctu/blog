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
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "ID_NGUOI_DUNG")
public class dau_sach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_SACH;

    @ManyToOne
    @JoinColumn(name="ID_NGUOI_DUNG",insertable = false,updatable = false)
    private nguoi_dung nguoiDung;
    private int ID_NGUOI_DUNG;

    private String TEN_SACH;
    private String HINH_SACH;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private String TOM_TAT;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int TRANG_THAI;


    @OneToMany(targetEntity = tl_ds.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_SACH",referencedColumnName = "ID_SACH")
    private List<tl_ds> tlDs;

    @OneToMany(targetEntity = cd_ds.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_SACH",referencedColumnName = "ID_SACH")
    private List<cd_ds> cdDs;

    @OneToMany(targetEntity = chapter.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_SACH",referencedColumnName = "ID_SACH")
    private List<chapter> chapters;

    @OneToMany(targetEntity = chapter.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_SACH",referencedColumnName = "ID_SACH")
    private List<danh_gia> danhGias;
}
