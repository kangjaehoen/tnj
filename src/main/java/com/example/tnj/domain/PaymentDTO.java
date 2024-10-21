package com.example.tnj.domain;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentDTO {
    private int resNum;
    private String id;
    private Date payDate;
    private char pay_Status;
    private String payment;

    //결제api
    private String amount;
    private int accomNum;
    private String impUid;
    private String merchantUid;
    private String name;
    private String productName;
    private String apply_num;

}
