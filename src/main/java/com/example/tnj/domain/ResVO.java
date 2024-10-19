package com.example.tnj.domain;

import lombok.*;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResVO {
    private int resNum;
    private Date resDate;
    private Date Chkin_Date;
    private Date Chkout_Date;
    private int adultCnt;
    private int kidCnt;
    private String id;
    private int accomNum;
}
