package com.example.tnj.domain;

import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccDetailDTO {
    //숙소테이블
    private int accomNum;
    private String id;
    private String accName;
    private String postcode;
    private String accCall;
    private int adultPrice;
    private int kidPrice;
    private Date dayoff;
    private String category;
    private String accType;
    private String accPic;
    private int onsale;
    private String accomRule;
    private int occ;
    private int maxocc;
    private int price;
    private String informtext;
    private Time chkin_Time;
    private Time chkout_Time;
    private String detailAddress;
    private int room;
    private int bed;
    private int bathroom;

    //리뷰테이블
    private int rnum;
    private int satisfy;
    private int accuracy;
    private int clean;
    private int scp;
    private Date hiredate;
    private String revContent;


}
