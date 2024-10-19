package com.example.tnj.controller;

import com.example.tnj.domain.WishListDTO;
import mybatis.dao.WishListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WishListController {
    @Autowired
    private WishListMapper wishListMapper;

    @GetMapping(value = "/wishCheck/{id}", produces = "application/json; charset=utf-8")
    public List<WishListDTO> wishCheck(@PathVariable String id){
        List<WishListDTO> list = wishListMapper.checkWish(id);
    return list;
    };


    @PostMapping(value = "/wish", produces = "application/json; charset=utf-8")
    public boolean wishClick(@RequestBody WishListDTO dto){
        boolean check;
        check = wishListMapper.wishListClick(dto);
        return check;

    }

   @GetMapping(value = "/checkWish/{accomNum}", produces = "application/json; charset=utf-8")
    public Boolean checkWishAccomNum(@PathVariable int accomNum){
        Boolean check = wishListMapper.checkWishAccomNum(accomNum) != null;
        return check;
    }

    @GetMapping(value = "/delete/{accomNum}",produces = "application/json; charset=utf-8")
    public Boolean deleteWish(@PathVariable int accomNum){
        Boolean check =wishListMapper.deleteWishAccomNum(accomNum);
        return check;
    }
}
