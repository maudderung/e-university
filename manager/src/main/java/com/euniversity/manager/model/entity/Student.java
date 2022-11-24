package com.euniversity.manager.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="facnum")
    long facNum;

    @Column(name="firstname")
    String firstName;

    @Column(name="lastname")
    String lastName;

    @Column(name="gradyear")
    int gradYear;
}
