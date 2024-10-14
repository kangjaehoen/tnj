package com.example.tnj.domain;

import lombok.Data;

import java.time.LocalDate;
@Data
public class AccProperty {
    String accomNum;
    String id;
    String postcode;
    String address;
    String accCall;
    int price;
    int adultPrice;
    int kidPrice;
    int ocupacy;
    int maximumOcupacy;
    LocalDate dayoff;
    String category;
    String accType;
    String accPic;
    int onSale;
    String accomRule;
    String informtext;
}
