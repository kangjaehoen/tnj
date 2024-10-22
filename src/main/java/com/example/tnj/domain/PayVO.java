package com.example.tnj.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter
public class PayVO {

    int resNum;
    String id;
    LocalDate payDate;
    String pay_Status;
    int accomNum;
    String impUid;
    String merchantUid;
    int amount;
    String name;
    String apply_num;
}
