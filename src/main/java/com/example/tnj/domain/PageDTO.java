package com.example.tnj.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageDTO {
    private int startNum;
    private int endNum;
    private int countNum;
    private int currentPage;

    private int total;

    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;


    public PageDTO(int startNum,int countNum,int currentPage,int total){
        this.startNum=startNum;
        this.countNum=countNum;
        this.currentPage=currentPage;
        this.total=total;


        this.endPage = (int) ((Math.ceil(this.currentPage/4.0))*4.0);
        this.startPage = this.endPage - 3;



        int realPage = (int) Math.ceil((total*1.0)/4);


        if(realPage<endPage) {
            this.endPage = realPage;
        }


        this.prev = this.startPage>1;
        this.next = this.endPage < realPage;

    }



}
