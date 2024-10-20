package com.example.tnj.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationDTO {
    //예약테이블
    private int resNum;
    private Date resDate;
    @JsonProperty("checkIn")
    private Date Chkin_Date;
    @JsonProperty("checkOut")
    private Date Chkout_Date;
    private int adultCnt;
    private int kidCnt;
    private String id;
    private int totalDays;
    private int totalPayment;

    //숙소테이블
    private int accomNum;
    private Time chkin_Time;
    private Time chkout_Time;
    private String accName;
    private int price;


    //리뷰테이블
    private int rnum;
    private int satisfy;
    private int accuracy;
    private int clean;
    private int scp;
    private Date hiredate;
    private String revContent;

}
