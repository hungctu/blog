package com.hungnguyen.blogweb.Repository;

import com.hungnguyen.blogweb.Model.nguoi_dung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NguoiDungRepository extends JpaRepository<nguoi_dung,Integer> {

    @Query(value = "select * from nguoi_dung where ID_NGUOI_DUNG=:id",nativeQuery = true)
    public nguoi_dung findbyid(@Param("id") int id);

    @Query(value = "select * from nguoi_dung where TEN_DANG_NHAP=:usn",nativeQuery = true)
    public nguoi_dung findbyusername(@Param("usn") String usn);

    @Query(value = "select * from nguoi_dung where email=:email",nativeQuery = true)
    public nguoi_dung findbyemail(@Param("email") String email);

    @Query(value = "select * from nguoi_dung where ID_NGUOI_DUNG=:id and TEN_DANG_NHAP=:usn",nativeQuery = true)
    public nguoi_dung findnd(@Param("id") int id,@Param("usn") String usn);

    @Query(value = "select * from nguoi_dung where TEN_DANG_NHAP=:usn and MAT_KHAU=:pass",nativeQuery = true)
    public nguoi_dung findnduap(@Param("usn") String usn,@Param("pass") String pass);
}
