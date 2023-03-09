package com.hungnguyen.blogweb.Repository;

import com.hungnguyen.blogweb.Model.chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ChapterRepository extends JpaRepository<chapter,Integer> {

    @Modifying
    @Query(value = "insert into chapter (id_sach, luot_xem, ngay_dang, noi_dung, ten_chapter) values(:id_sach,0,:ngay_dang,:noi_dung,:ten_chapter)",nativeQuery = true)
    public int insertchapter(@Param("id_sach") int id_sach, @Param("ngay_dang") Date ngay_dang,
                                @Param("noi_dung") String noi_dung,@Param("ten_chapter") String ten_chapter);

    @Query(value = "SELECT distinct * FROM chapter c where c.ID_SACH=:dsid",nativeQuery = true)
    public List<chapter> findchaptheodsid(@Param("dsid") int id);

    @Query(value = "select * from chapter where ID_CHAPTER=:cid",nativeQuery = true)
    public chapter findchapterbyid(@Param("cid") int id);
}
