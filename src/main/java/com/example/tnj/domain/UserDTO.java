package com.example.tnj.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor
public class UserDTO {
    private String id;
    private String name;
    private String email;
    private String pw;
    private String gender;
    private String birth;
    private String accountNum;

    public UserDTO(String id, String pw){
        this.id = id;
        this.pw = pw;
    }
    public UserDTO(String id){
        this.id = id;
    }
    public UserDTO(){
    }
}
