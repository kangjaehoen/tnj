package com.example.tnj.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WishListDTO {
    private int wishNum;
    private String id;
    private int accomNum;
    private int checkStatus;
}
