package org.openwebinars.course.gettingStarted;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// Panache via Repository Pattern
@Entity
public class DeveloperWithPanache extends PanacheEntity {
    // There is an id by default
//    @Id
//    private Integer id;

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Column(unique = true)
//    private String name;

// It can be annotated as public, because when quarkus compiles -> it already changes to the autogenerated accessors
    @Column(unique = true)
    public String name;

    // Added once you have got already data
    @Column()
    public Integer age;

}
