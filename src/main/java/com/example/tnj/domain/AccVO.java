package com.example.tnj.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccVO {
    String accName;
    int accomNum;
    String id;
    String postcode;
    String address;
    String detailAddress;
    String accCall;
    int price;
    int adultPrice;
    int kidPrice;
    int occ;
    int maxocc;
    LocalDate dayoff;
    String category;
    String accType;
    String accPic;
    int onSale;
    String accomRule;
    String informtext;
    LocalTime chkin_Time;
    LocalTime chkout_Time;
    int room;
    int bed;
    int bathroom;
}
