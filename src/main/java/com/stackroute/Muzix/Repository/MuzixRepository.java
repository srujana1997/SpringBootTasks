package com.stackroute.Muzix.Repository;

import com.stackroute.Muzix.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//Repository class used to perform database operations, extends JpaRepository
//JpaRepository<EntityClassName,WrapperTypeOfIdPropertyInEntityClass>
@Repository
public interface MuzixRepository extends JpaRepository<Track,Integer> {

}
