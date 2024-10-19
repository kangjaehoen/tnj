package com.example.tnj.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryVO {
    private String accName;
    private int accomNum;
    private String id;
    private String postcode;
    private String address;
    private String detailAddress;
    private String accCall;
    private String price;
    private int adultPrice;
    private int kidPrice;
    private int occ;
    private int maxocc;
    private LocalDate dayoff;
    private String category;
    private String accType;
    private int onSale;
    private String accomRule;
    private String informtext;
    private LocalTime chkin_Time;
    private LocalTime chkout_Time;
    private int room;
    private int bed;
    private int bathroom;

    private int satisfy;
    private double satisAvg;
}
