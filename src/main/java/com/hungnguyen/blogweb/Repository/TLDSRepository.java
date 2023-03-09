package com.hungnguyen.blogweb.Repository;

import com.hungnguyen.blogweb.Model.tl_ds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TLDSRepository extends JpaRepository<tl_ds,Integer> {


    @Modifying
    @Query(value = "insert into tl_ds (ID_TL,ID_SACH) values(:id_tl,:id_sach)",nativeQuery = true)
    public int inserttlds(@Param("id_tl") int id_tl, @Param("id_sach") int id_sach);

    @Query(value = "SELECT td.ID_TL FROM tl_ds td where td.ID_SACH=:dsid",nativeQuery = true)
    public List<Integer> findidtldstheodsid(@Param("dsid") int id);

    @Query(value = "SELECT * FROM tl_ds td where td.ID_SACH=:dsid and td.ID_TL=:tlid",nativeQuery = true)
    public tl_ds findtlds(@Param("dsid") int id,@Param("tlid") int tlid);
}
