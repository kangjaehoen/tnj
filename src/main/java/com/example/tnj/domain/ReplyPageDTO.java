package com.example.tnj.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReplyPageDTO {
    private List<ReplyDTO> dto;
    private PageDTO pdto;
}
