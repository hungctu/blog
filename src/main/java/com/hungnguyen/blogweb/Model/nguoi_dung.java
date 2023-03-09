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
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property =  "@id")
public class nguoi_dung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_NGUOI_DUNG;
    private String TEN_NGUOI_DUNG;
    private String Hinh_ND;
    private String TEN_DANG_NHAP;
    private String MAT_KHAU;
    private String email;

    @OneToMany(targetEntity = dau_sach.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_NGUOI_DUNG",referencedColumnName = "ID_NGUOI_DUNG")
    private List<dau_sach> dausaches;

    @OneToMany(targetEntity = binh_luan.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_NGUOI_DUNG",referencedColumnName = "ID_NGUOI_DUNG")
    private List<binh_luan> binhLuans;

    @OneToMany(targetEntity = dau_sach.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_NGUOI_DUNG",referencedColumnName = "ID_NGUOI_DUNG")
    private List<danh_gia> danhGias;
}
