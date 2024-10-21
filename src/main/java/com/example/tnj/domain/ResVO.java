package com.example.tnj.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter
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
