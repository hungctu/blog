package com.hungnguyen.blogweb.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "@Id")
public class binh_luan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_BINH_LUAN;

    @ManyToOne
    @JoinColumn(name = "ID_CHAPTER",insertable = false,updatable = false)
    private chapter Chapter;
    private int ID_CHAPTER;

    @ManyToOne
    @JoinColumn(name = "ID_NGUOI_DUNG",insertable = false,updatable = false)
    private nguoi_dung nguoiDung;

    private int ID_NGUOI_DUNG;
    private String NOI_DUNG_BL;
    private Date NGAY_BL;
}
