package com.example.tnj.controller;

import com.example.tnj.domain.AccVO;
import com.example.tnj.domain.AccomImageVO;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class AccRegistController {
    @Autowired
    AccomMapper acmd;
    @Autowired
    AccomImageMapper acimg;
    @Autowired
    WishListMapper wl;

    @RequestMapping(value="/accLoad", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ModelAndView accload(HttpSession session, /*@RequestParam(defaultValue = "0")*/ int accomNum) {
        System.out.println(session.getAttribute("accomNum"));
        String id = (String) session.getAttribute("id");
        ModelAndView mav = new ModelAndView();
        AccVO ac = acmd.oneacc(id, accomNum);
        mav.addObject("acmd", ac);
        mav.setViewName("accRegister");
        return mav;
    }

    @RequestMapping("/accUpload")
    @ResponseBody
    public ModelAndView accupload(@ModelAttribute AccVO ac,
                                  @RequestParam(required = false) List<MultipartFile> imageUpload, @ModelAttribute AccomImageVO ai) {
        acmd.accinsert(ac);
        ai.setAccomNum(ac.getAccomNum());
        ModelAndView mav = new ModelAndView();
        if (imageUpload != null) {
        String path = "c:/kosa/project1/"+ac.getAccomNum();
        File isDir = new File(path);
        if (!isDir.exists()) {
            isDir.mkdir();
        }
            List<MultipartFile> list = imageUpload;
            if (!list.isEmpty()) {
                for (MultipartFile mfile : list) {
                    try {
                        String originalFileName = mfile.getOriginalFilename();
                        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
                        String filePath = String.valueOf(path + "/" + UUID.randomUUID().toString() + extension);
                        ai.setFilePath(filePath);
                        acimg.insertimg(ai);
                        File imgF = new File(filePath);
                        mfile.transferTo(imgF);
                    } catch (IOException | StringIndexOutOfBoundsException | IllegalStateException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        mav.setViewName("redirect:/myacclist");
        return mav;
    }

    @RequestMapping("/accDelete")
    @ResponseBody
    public ModelAndView accDelete(HttpSession session,@RequestParam("loadedAccomNum") int accomNum) {
        ModelAndView mav = new ModelAndView();
        wl.deleteByAccomNum(accomNum);
        acimg.deleteimg(accomNum);
        acmd.accdelete((String)session.getAttribute("id"), accomNum);
        String path = "c:/kosa/project1/"+accomNum;
        File isDir = new File(path);
        if(isDir.exists()) {
            File[] files = isDir.listFiles();
            if (files != null)
                for (File f : files) {
                    f.delete();
                }
            isDir.delete();
        }
        mav.setViewName("redirect:/myacclist");
        return mav;
    }

    @RequestMapping("/accUpdate")
    @ResponseBody
    public ModelAndView accUpdate(AccVO ac, int loadedAccomNum, @RequestParam(required = false) List<MultipartFile> imageUpload, @ModelAttribute AccomImageVO ai) {
        ModelAndView mav = new ModelAndView();
        acmd.accupdate(ac, loadedAccomNum);
        ai.setAccomNum(loadedAccomNum);
        mav.setViewName("redirect:/myacclist");

        if (imageUpload != null) {
            acimg.deleteimg(loadedAccomNum);
            String path = "c:/kosa/project1/"+loadedAccomNum;
            File isDir = new File(path);
            if (!isDir.exists()) {
                isDir.mkdir();
            }
            else {
                File[] files = isDir.listFiles();
                if(files != null)
                for (File f : files) {
                    f.delete();
                }
            }
            List<MultipartFile> list = imageUpload;
            if (!list.isEmpty()) {
                for (MultipartFile mfile : list) {
                    try {
                        String originalFileName = mfile.getOriginalFilename();
                        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
                        String filePath = String.valueOf(path + "/" + UUID.randomUUID().toString() + extension);
                        ai.setFilePath(filePath);
                        acimg.insertimg(ai);
                        File imgF = new File(filePath);
                        mfile.transferTo(imgF);
                    } catch (IOException | StringIndexOutOfBoundsException | IllegalStateException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return mav;
    }
}
