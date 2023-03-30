package com.microservices.userservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "microservice_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "ID")
    private String userId;

    @Column(name = "NAME", length = 20)
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ABOUT")
    private String about;

    //Transient matlab DB mei store nahi karna
    @Transient
    private List<Rating> rating = new ArrayList<>();
}
