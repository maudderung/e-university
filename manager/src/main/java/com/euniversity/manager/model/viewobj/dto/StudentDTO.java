package com.euniversity.manager.model.viewobj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {

    long facNum;

    String firstName;

    String lastName;

    int gradYear;

    boolean isUpdated;
}
