package com.example.tnj.domain;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PayVO {
    private String id;
    private LocalDate payDate;
    private int pay_Staus;
    private int payment;
    private int accomNum;
    private int resNum;
}
