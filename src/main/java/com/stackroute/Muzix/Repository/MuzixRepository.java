package com.stackroute.Muzix.Repository;

import com.stackroute.Muzix.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuzixRepository extends JpaRepository<Track,Integer> {
    @Query(value = "select * from Track where name=?1",nativeQuery = true)
    List<Track> findTitleByName(String name);
}
