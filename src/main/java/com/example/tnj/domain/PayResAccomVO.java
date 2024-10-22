package com.example.tnj.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PayResAccomVO {
    private LocalDate payDate;
    private int accomNum;
    private String amount;
    private String impUid;
    @JsonProperty("checkIn")
    private Date Chkin_Date;
    @JsonProperty("checkOut")
    private Date Chkout_Date;
    private int adultCnt;
    private int kidCnt;
    private String id;
    private String accName;
    private String postcode;
    private String address;
    private String detailAddress;
    private String accCall;
    private int price;
    private LocalTime chkin_Time;
    private LocalTime chkout_Time;
    private int reviewCount;
    private double satisAvg;
    private String totalDay;
    private int resNum;

}
