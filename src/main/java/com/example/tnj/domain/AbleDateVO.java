package com.example.tnj.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter@Setter@AllArgsConstructor
public class AbleDateVO {
    int accomNum;
    LocalDate date;
    String ableStatus;
}
