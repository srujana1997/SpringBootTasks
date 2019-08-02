package com.stackroute.Muzix.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@PropertySource("com.stackroute.resources")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String comment;

}
