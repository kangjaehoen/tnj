package com.example.tnj.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReplyRatingVO {
    private int reviewCount;
    private int count5;
    private int count4;
    private int count3;
    private int count2;
    private int count1;
    private int satisAvg;
    private int accuracyAvg;
    private int cleanAvg;
    private int scpAvg;

}
