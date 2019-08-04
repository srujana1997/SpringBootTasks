package com.stackroute.Muzix;

import com.fasterxml.jackson.annotation.JsonTypeId;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
//@Entity used to mark it as a database entity
@Entity
//@Table(name="Track")
//@DataAmount
//@NoArgsConstructor
//@Builder
public class Track {
    //annotated with @Id to make it primary key in database
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id; //track's id
    @Column
    private String name;// track's name
    @Column
    private String comment;//track's comment
    //all argument constructor
    public Track(int id, String name, String comment) {
        this.id = id;
        this.name = name;
        this.comment = comment;
    }
    //no argument constructor
    public  Track(){

    }
    //setter and getter injections
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
