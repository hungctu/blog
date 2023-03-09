package com.hungnguyen.blogweb.Repository;

import com.hungnguyen.blogweb.Model.dau_sach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DauSachRepository extends JpaRepository<dau_sach,Integer> {

    @Query(value = "SELECT distinct d.* FROM dau_sach d,chapter c where d.ID_SACH=c.ID_SACH and TRANG_THAI=1 order by c.NGAY_DANG desc",nativeQuery = true)
    public List<dau_sach> findalltt();

    @Query(value = "SELECT distinct d.* FROM dau_sach d,chapter c where d.ID_SACH=c.ID_SACH and TRANG_THAI=1 order by c.NGAY_DANG desc" +
            " limit :dbd , :ktt",nativeQuery = true)
    public List<dau_sach> findallpt(@Param("dbd") int dbd,@Param("ktt") int ktt);

    @Query(value = "select * from dau_sach where ID_SACH=:id",nativeQuery = true)
    public dau_sach findbyid(@Param("id") int id);

    @Query(value = "select * from dau_sach where TEN_SACH=:name",nativeQuery = true)
    public dau_sach findbyname(@Param("name") String name);

    @Query(value = "select distinct d.* from dau_sach d,chapter c where d.TEN_SACH LIKE %:name% and  d.ID_SACH=c.ID_SACH and TRANG_THAI=1 order by c.NGAY_DANG desc",nativeQuery = true)
    public List<dau_sach> timkiem(@Param("name") String timkiem);
    @Query(value = "select distinct d.* from dau_sach d,chapter c where d.TEN_SACH LIKE %:name% and  d.ID_SACH=c.ID_SACH and TRANG_THAI=1 order by c.NGAY_DANG desc " +
            "limit :dbd , :ktt",nativeQuery = true)
    public List<dau_sach> timkiempt(@Param("name") String timkiem,@Param("dbd") int dbd,@Param("ktt") int ktt);

    @Query(value = "SELECT distinct d.* FROM dau_sach d,tl_ds td,chapter c" +
            " where d.ID_SACH=c.ID_SACH and d.ID_SACH = td.ID_SACH and td.ID_TL=:tlid and TRANG_THAI=1 order by c.NGAY_DANG desc ",nativeQuery = true)
    public List<dau_sach> finddstheotl(@Param("tlid") int id);
    @Query(value = "SELECT distinct d.* FROM dau_sach d,tl_ds td,chapter c" +
            " where d.ID_SACH=c.ID_SACH and d.ID_SACH = td.ID_SACH and td.ID_TL=:tlid and TRANG_THAI=1 order by c.NGAY_DANG desc " +
            "limit :dbd , :ktt",nativeQuery = true)
    public List<dau_sach> finddstheotlpt(@Param("tlid") int id,@Param("dbd") int dbd,@Param("ktt") int ktt);

    @Query(value = "SELECT distinct d.* FROM dau_sach d,cd_ds cd,chapter c" +
            " where d.ID_SACH=c.ID_SACH and d.ID_SACH = cd.ID_SACH and cd.ID_CD=:cdid and TRANG_THAI=1 order by c.NGAY_DANG desc ",nativeQuery = true)
    public List<dau_sach> finddstheocd(@Param("cdid") int id);

    @Query(value = "SELECT distinct d.* FROM dau_sach d,cd_ds cd,chapter c" +
            " where d.ID_SACH=c.ID_SACH and d.ID_SACH = cd.ID_SACH and cd.ID_CD=:cdid and TRANG_THAI=1 order by c.NGAY_DANG desc " +
            "limit :dbd , :ktt",nativeQuery = true)
    public List<dau_sach> finddstheocdpt(@Param("cdid") int id,@Param("dbd") int dbd,@Param("ktt") int ktt);

    @Query(value = "SELECT distinct d.* FROM dau_sach d,chapter c" +
            " where d.ID_SACH=c.ID_SACH and d.ID_NGUOI_DUNG=:ndid order by c.NGAY_DANG desc",nativeQuery = true)
    public List<dau_sach> finddstheond(@Param("ndid") int id);

    @Query(value = "SELECT d.* FROM dau_sach d, chapter c where d.ID_SACH = c.ID_SACH group by d.ID_SACH order by sum(LUOT_XEM) desc",nativeQuery = true)
    public List<dau_sach> finddsnhieuluotxem();
}
