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
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "ID_CHAPTER")
public class chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_CHAPTER;

    @ManyToOne
    @JoinColumn(name = "ID_SACH",insertable = false,updatable = false)
    private dau_sach dauSach;
    private int ID_SACH;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String TEN_CHAPTER;

    private String NOI_DUNG;

    @Column(name="NGAY_DANG", columnDefinition="DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date NGAY_DANG;

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int LUOT_XEM;

    /*@OneToMany(mappedBy = "Chapter")
    private List<binh_luan> binhluans;*/
}
