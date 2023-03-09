package com.hungnguyen.blogweb.Repository;

import com.hungnguyen.blogweb.Model.chu_de;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChuDeRepository extends JpaRepository<chu_de,Integer> {

    @Query(value = "select * from chu_de where ID_CD=:id",nativeQuery = true)
    public chu_de findbyid(@Param("id") int id);

    @Query(value = "select * from chu_de where TEN_CD=:name",nativeQuery = true)
    public chu_de findbyname(@Param("name") String name);
}
