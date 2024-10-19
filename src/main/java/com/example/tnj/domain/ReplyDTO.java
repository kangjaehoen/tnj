package com.example.tnj.domain;

import lombok.*;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReplyDTO {
    private int rnum;
    private int satisfy;
    private int accuracy;
    private int clean;
    private int scp; // 가격대비 만족도
    private String revContent;
    private String accomNum;
    private String id;
    private String hiredate;
}
