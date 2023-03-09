package com.hungnguyen.blogweb.Repository;

import com.hungnguyen.blogweb.Model.danh_gia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DanhGiaRepository  extends JpaRepository<danh_gia,Integer> {

    @Query(value = "SELECT count(ID_DG) FROM danh_gia d,dau_sach ds where d.ID_SACH=ds.ID_SACH and ds.ID_NGUOI_DUNG=:userid and LOAI_DG=:loaidg",nativeQuery = true)
    public int tongluotdanhgia(@Param("userid") int id,@Param("loaidg") int loai);

    @Query(value = "SELECT count(ID_DG) FROM danh_gia where ID_SACH=:dsid and LOAI_DG=:loaidg",nativeQuery = true)
    public int luotdanhgia(@Param("dsid") int id,@Param("loaidg") int loai);

    @Query(value = "SELECT * FROM danh_gia where ID_SACH=:dsid and ID_NGUOI_DUNG=:userid",nativeQuery = true)
    public danh_gia finddanhgia(@Param("dsid") int id,@Param("userid") int uid);
}
