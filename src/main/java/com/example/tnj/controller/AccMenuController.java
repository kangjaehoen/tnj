package com.example.tnj.controller;

import com.example.tnj.domain.AccVO;
import jakarta.servlet.http.HttpSession;
import mybatis.dao.AccomImageMapper;
import mybatis.dao.AccomMapper;
import mybatis.dao.WishListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;

@Controller
public class AccMenuController {
    @Autowired
    AccomMapper acmd;
    @Autowired
    AccomImageMapper acimg;
    @Autowired
    WishListMapper wl;

    @RequestMapping(value = "/accregist")
    @ResponseBody
    public ModelAndView accregist() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("accRegister");
        return mav;
    }

    @RequestMapping(value = "/myacclist")
    @ResponseBody
    public ModelAndView myacclist(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        String id = (String) session.getAttribute("id");
        List<AccVO> mylist = acmd.listmine(id);
        mav.addObject("mylist", mylist);
        mav.setViewName("myAccommodations");
        return mav;
    }

    @RequestMapping(value = "/searchmine")
    @ResponseBody
    public ModelAndView searchmine(HttpSession session, @RequestParam(defaultValue = "") String key) {
        ModelAndView mav = new ModelAndView();
        String id = (String) session.getAttribute("id");
        List<AccVO> mylist = acmd.searchmine(id, key);
        mav.addObject("mylist", mylist);
        mav.setViewName("myAccommodations");
        return mav;
    }
    @RequestMapping(value = "/searchonsale")
    @ResponseBody
    public ModelAndView showonsale(HttpSession session){
        ModelAndView mav = new ModelAndView();
        String id = (String) session.getAttribute("id");
        List<AccVO> mylist = acmd.searchmineonsale(id);
        mav.addObject("mylist", mylist);
        mav.setViewName("myAccommodations");
        return mav;
    }

    @RequestMapping("/accDeletelist")
    @ResponseBody
    public ModelAndView accDeletelist(HttpSession session, @RequestParam("dlist") String dlist) {
        String[] dStringary = dlist.split(",");
        ModelAndView mav = new ModelAndView();
        for (String dString : dStringary) {
            int accomNum = Integer.parseInt(dString);
            wl.deleteByAccomNum(accomNum);
            acimg.deleteimg(accomNum);
            acmd.accdelete((String) session.getAttribute("id"), accomNum);
            String path = "c:/kosa/project1/" + accomNum;
            File isDir = new File(path);
            if (isDir.exists()) {
                File[] files = isDir.listFiles();
                if (files != null)
                    for (File f : files) {
                        f.delete();
                    }
                isDir.delete();
            }
        }
        mav.setViewName("redirect:/myacclist");
        return mav;
    }

    @RequestMapping("/accSalelist")
    @ResponseBody
    public ModelAndView saleupdatelist(HttpSession session, String slist) {
        String[] sStringary = slist.split(",");
        ModelAndView mav = new ModelAndView();
        for (String sString : sStringary) {
            int accomNum = Integer.parseInt(sString);
            if (acmd.isOnSale(accomNum) == 1)
                try {
                    acmd.saleupdate(accomNum, 0);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            else if (acmd.isOnSale(accomNum) == 0)
                acmd.saleupdate(accomNum, 1);
        }
        mav.setViewName("redirect:/myacclist");
        return mav;
    }
}