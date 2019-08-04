package com.stackroute.Muzix;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
//@Entity used to mark it as a database entity
@Entity
//Lombok plugin automatically generates getters, setters and constructors for any class marked as @Data
@Data
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track {
    //annotated with @Id to make it primary key in database
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; //track's id
    private String name;//track's name
    private String comment;//track's comment

}
