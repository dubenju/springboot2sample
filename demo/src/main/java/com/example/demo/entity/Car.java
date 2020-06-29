package com.example.demo.entity;
import javax.persistence.Id;

import lombok.Data;

//import org.springframework.hateoas.ResourceSupport;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
//public class Car extends ResourceSupport {
//public class Car extends RepresentationModel<AddressRestResponseModel> {
public class Car {
    @Id @GeneratedValue
    private Long id;
    private @NonNull String name;

}
