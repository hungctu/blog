package com.hungnguyen.blogweb.Repository;

import com.hungnguyen.blogweb.Model.cd_ds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CDDSRepository extends JpaRepository<cd_ds,Integer> {

    @Modifying
    @Query(value = "insert into cd_ds (ID_CD,ID_SACH) values(:id_cd,:id_sach)",nativeQuery = true)
    public int insertcdds(@Param("id_cd") int id_cd, @Param("id_sach") int id_sach);

    @Query(value = "SELECT cd.ID_CD FROM cd_ds cd where cd.ID_SACH=:dsid",nativeQuery = true)
    public List<Integer> findidcddstheodsid(@Param("dsid") int id);

    @Query(value = "SELECT * FROM cd_ds cd where cd.ID_SACH=:dsid and cd.ID_CD=:cdid",nativeQuery = true)
    public cd_ds findcdds(@Param("dsid") int id, @Param("cdid") int cdid);
}
