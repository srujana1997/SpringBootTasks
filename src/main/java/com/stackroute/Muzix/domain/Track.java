package com.stackroute.Muzix.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
//@Document used to mark it as a database document 
@Document
//Lombok plugin automatically generates getters, setters and constructors for any class marked as @Data
@Data
@NoArgsConstructor
@AllArgsConstructor
@PropertySource("com.stackroute.resources")
public class Track {
    //annotated with @Id to make it primary key in database
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;//track's id
    private String name;//track's name
    private String comment;//track's comment

}
