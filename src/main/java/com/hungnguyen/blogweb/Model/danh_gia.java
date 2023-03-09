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
public class danh_gia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_DG;

    @ManyToOne
    @JoinColumn(name="ID_NGUOI_DUNG",insertable = false,updatable = false)
    private nguoi_dung nguoiDung;
    private int ID_NGUOI_DUNG;

    @ManyToOne
    @JoinColumn(name = "ID_SACH",insertable = false,updatable = false)
    private dau_sach dauSach;
    private int ID_SACH;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int LOAI_DG;
}
