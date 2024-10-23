package com.example.tnj.domain;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResVO {
    int resNum;
    LocalDate resDate;
    LocalDate chkin_Date;
    LocalDate chkout_Date;
    int adultCnt;
    int kidCnt;
    String id;
    int accomNum;
}
