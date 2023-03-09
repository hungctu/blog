package com.hungnguyen.blogweb.Repository;

import com.hungnguyen.blogweb.Model.binh_luan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BinhLuanRepository extends JpaRepository<binh_luan,Integer> {

    @Query(value = "select * from binh_luan where ID_CHAPTER=:chapid order by NGAY_BL desc",nativeQuery = true)
    List<binh_luan> findbychapid(@Param("chapid") int id);
}
