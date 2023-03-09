package com.hungnguyen.blogweb.Repository;

import com.hungnguyen.blogweb.Model.the_loai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TheLoaiRepository extends JpaRepository<the_loai,Integer> {

    @Query(value = "select * from the_loai where ID_TL=:id",nativeQuery = true)
    public the_loai findbyid(@Param("id") int id);

    @Query(value = "select * from the_loai where TEN_TL=:name",nativeQuery = true)
    public the_loai findbyname(@Param("name") String name);
}
