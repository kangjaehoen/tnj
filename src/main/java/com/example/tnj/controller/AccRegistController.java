package com.example.tnj.controller;

import com.example.tnj.domain.AbleDateVO;
import com.example.tnj.domain.AccVO;
import com.example.tnj.domain.AccomImageVO;
import jakarta.servlet.http.HttpSession;
import mybatis.dao.AbledateMapper;
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
import java.time.LocalDate;
import java.util.*;

@Controller
public class AccRegistController {
    @Autowired
    AccomMapper acmd;
    @Autowired
    AccomImageMapper acimg;
    @Autowired
    WishListMapper wl;
    @Autowired
    AbledateMapper ab;

    @RequestMapping(value = "/accLoad")
    @ResponseBody
    public ModelAndView accload(HttpSession session, int accomNum) {
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
                                  @RequestParam(required = false) List<MultipartFile> imageUpload, @ModelAttribute AccomImageVO ai, @RequestParam(value = "day", required = false) List<String> days) {
        //휴일데이터 넣기
        if (days != null && !days.isEmpty()) {
            String dayoff = String.join(",", days);
            ac.setDayoff(dayoff);
        }
        //accom 테이블에 데이터 넣음
        acmd.accinsert(ac);
        //abledate 테이블에 날짜넣기
        LocalDate d = ac.getRegDate();
        Set<Integer> intdayoff = new HashSet<>();
        if (days != null && !days.isEmpty()) {
            for (String s : days) {
                intdayoff.add(Integer.parseInt(s));
            }
        }
        ArrayList<AbleDateVO> ableDates = new ArrayList<>();
        for (int i = 0; i < 365; i++) {
            LocalDate day = d.plusDays(i);
            if (intdayoff.contains(day.getDayOfWeek().getValue())) {
                ableDates.add(new AbleDateVO(ac.getAccomNum(), day, "disable"));
                //ab.createdate(ac.getAccomNum(), d.plusDays(i), "disable");
            } else {
                ableDates.add(new AbleDateVO(ac.getAccomNum(), day, "able"));
                //ab.createdate(ac.getAccomNum(), d.plusDays(i), "able");
            }
        }
        ab.batchInsertDates(ableDates);

        //이미지 넣기
        ai.setAccomNum(ac.getAccomNum());
        ModelAndView mav = new ModelAndView();
        if (imageUpload != null) {
            String path = "c:/kosa/project1/" + ac.getAccomNum();
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
    public ModelAndView accDelete(HttpSession session, @RequestParam("loadedAccomNum") int accomNum) {
        ModelAndView mav = new ModelAndView();
        ab.deleteabledates(accomNum);
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
        mav.setViewName("redirect:/myacclist");
        return mav;
    }

    @RequestMapping("/accUpdate")
    @ResponseBody
    public ModelAndView accUpdate(AccVO ac, int loadedAccomNum, @RequestParam(required = false) List<MultipartFile> imageUpload, @ModelAttribute AccomImageVO ai, @RequestParam(value = "day", required = false) List<String> days) {
        ModelAndView mav = new ModelAndView();
        //휴일
        if (days != null && !days.isEmpty()) {
            String dayoff = String.join(",", days);
            ac.setDayoff(dayoff);
        }
        //accom 테이블 내용 수정
        acmd.accupdate(ac, loadedAccomNum);
        //기존값 삭제(예약안된것만)
        ab.deleteabledates(loadedAccomNum);
        //다시 추가
        LocalDate d = ac.getRegDate();
        Set<Integer> intdayoff = new HashSet<>();
        if (days != null && !days.isEmpty()) {
            for (String s : days) {
                intdayoff.add(Integer.parseInt(s));
            }
        }
        ArrayList<AbleDateVO> ableDates = new ArrayList<>();
        List<AbleDateVO> prelist = ab.ableList(loadedAccomNum);
        List<LocalDate> prelistdate = ab.ableListdate(loadedAccomNum);
        for (int i = 0; i < 365; i++) {
            LocalDate day = d.plusDays(i);
            if (!prelistdate.contains(day)) {
                if (intdayoff.contains(day.getDayOfWeek().getValue())) {
                    ableDates.add(new AbleDateVO(loadedAccomNum, day, "disable"));
                } else {
                    ableDates.add(new AbleDateVO(loadedAccomNum, day, "able"));
                }
            }
        }
        ableDates.addAll(prelist);
        ab.batchInsertDates(ableDates);

        //이미지 삭제후 저장
        ai.setAccomNum(loadedAccomNum);
        if (imageUpload != null) {
            acimg.deleteimg(loadedAccomNum);
            String path = "c:/kosa/project1/" + loadedAccomNum;
            File isDir = new File(path);
            if (!isDir.exists()) {
                isDir.mkdir();
            } else {
                File[] files = isDir.listFiles();
                if (files != null)
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
        mav.setViewName("redirect:/myacclist");
        return mav;
    }
}
