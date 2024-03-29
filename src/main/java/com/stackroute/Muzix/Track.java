package com.stackroute.Muzix;

import com.fasterxml.jackson.annotation.JsonTypeId;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
//@Entity used to mark it as a database entity
@Entity
public class Track {
    //annotated with @Id to make it primary key in database
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;  //Track's id
    @Column
    private String name;     //Track's name
    @Column
    private String comment;     //Track's comment
    //all Arguments constructor
    public Track(int id, String name, String comment) {
        this.id = id;
        this.name = name;
        this.comment = comment;
    }
    //No args constructor
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
