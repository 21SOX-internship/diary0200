package com.example.diary_0200.Controller;

import com.example.diary_0200.DAO.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyPageController {

    @RequestMapping(value = "/mypage/main")
    public String gomypage(HttpServletRequest request, Model model) {

        //세션 불러오기
        HttpSession session = request.getSession();
        int seq = 0;
        if (session.getAttribute("seq") != null) {
            seq = (int) session.getAttribute("seq");
        }
        System.out.println("seq : " + seq);

        //dao 객체 생성
        mypageDAO dao = new mypageDAO();


        String mypageName = dao.loadMyPageName(seq);

        ResultSet mypageInfo = dao.loadMyPageInfo(seq);
//        try {
//            mypageInfo.next();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        //회원번호별 날짜 추출
        ResultSet date = dao.getDate(seq);

        ArrayList<String> dateList = new ArrayList<>();
        try {
            if (date.next()) {
                do {
                    dateList.add(date.getString("date"));
                } while (date.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*테스트 코드*/
        for (int i = 0; i < dateList.size(); i++) {
            System.out.println("회원번호별 날짜 추출 date : " + dateList.get(i));
        }

        //해당 날짜 중에서 수행시간이 가장 높은 목표 추출
        ArrayList<ResultSet> goalList = new ArrayList<>();
        for (int i = 0; i < dateList.size(); i++) {
            try {
                ResultSet temp = dao.getHighestTime(dateList.get(i), seq);
                temp.next();
                goalList.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        /*테스트코드*/
//        for(int i=0; i<goalList.size(); i++) {
//            try{
//                System.out.println("goalList date : "+goalList.get(i).getString("date"));
//                System.out.println("goalList time : "+goalList.get(i).getString("time"));
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }

        model.addAttribute("mypageName", mypageName);
        model.addAttribute("mypageInfo", mypageInfo);
        model.addAttribute("goalList", goalList);

        folderDAO folderdao = new folderDAO();
        ArrayList<folderDTO> folders = folderdao.getfolder(seq);
        System.out.println(folders);
        model.addAttribute("folders", folders);


        return "mypage";
    }

    @RequestMapping(value = "/mypage/edit")
    public String gomypageedit() {


        return "mypage_edit";
    }

//    @RequestMapping("/file/edit/save")
//    public String upload(HttpServletRequest request, @RequestBody MultipartFile uploadFile) {
//        String filePath = request.getRealPath("/");
//
//        File file = new File(filePath, uploadFile.getOriginalFilename());
//
//        try {
//            if (!uploadFile.isEmpty()) {
//                uploadFile.transferTo(file);
//                request.setAttribute("result", "ok");
//                request.setAttribute("message", "파일이 저장되었습니다.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "mypage";
//    }

    @RequestMapping(value = "/mypage/createfolder")
    public String gomypagegoal(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        int seq = 0;
        if (session.getAttribute("seq") != null) {
            seq = (int) session.getAttribute("seq");
        }

        mypageDAO dao = new mypageDAO();

        ResultSet mypageInfo = dao.loadMyPageInfo(seq);

        //회원번호별 날짜 추출
        ResultSet date = dao.getDate(seq);

        ArrayList<String> dateList = new ArrayList<>();
        try {
            if (date.next()) {
                do {
                    dateList.add(date.getString("date"));
                } while (date.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*테스트 코드*/
        for (int i = 0; i < dateList.size(); i++) {
            System.out.println("회원번호별 날짜 추출 date : " + dateList.get(i));
        }

        //해당 날짜 중에서 수행시간이 가장 높은 목표 추출
        ArrayList<ResultSet> goalList = new ArrayList<>();
        for (int i = 0; i < dateList.size(); i++) {
            try {
                ResultSet temp = dao.getHighestTime(dateList.get(i), seq);
                temp.next();
                goalList.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        model.addAttribute("mypageInfo", mypageInfo);
        model.addAttribute("goalList", goalList);


        return "mypage_create_folder";
    }

    @PostMapping("/mypage/edit/save")
    public String upload(HttpServletRequest request, @RequestParam("photo") MultipartFile uploadFile) {

        HttpSession session = request.getSession();
        int seq = 0;
        if (session.getAttribute("seq") != null) {
            seq = (int) session.getAttribute("seq");
        }
        System.out.println("seq 확인 : " + seq);

        mypageDAO dao = new mypageDAO();
        String realPath = request.getServletContext().getRealPath("/upload");
        System.out.println("realPath 확인 : "+realPath);


        String message = request.getParameter("message");
//        System.out.println("message 확인 : "+message);

        dao.saveMessage(seq, message);


        File currentDirPath = new File(realPath);
//        System.out.println("currentDirPath 확인 : "+currentDirPath);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(currentDirPath);
        factory.setSizeThreshold(1024 * 1024 * 5);
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

        try {
            File file = new File(realPath + "/" + seq + ".png");
            uploadFile.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        request.setAttribute("uploadPath", realPath);

        return "redirect:/mypage/main";
    }

    @RequestMapping("/mypage/pastgoal")
    public String gopastgoal(@RequestParam("date") String date, HttpServletRequest request, Model model) {
        goalTDAO goaltdao = new goalTDAO();
        goalSwDAO goalswdao = new goalSwDAO();

        int seq = 0;
        HttpSession session = request.getSession();
        if (session.getAttribute("seq") != null) {
            seq = (int) session.getAttribute("seq");
        }
        int friendSeq = 0;
        String temp = request.getParameter("friendSeq");
        if(temp == null){
            friendSeq = seq;
        }else{
            friendSeq = Integer.parseInt(temp);
        }

        ArrayList<goalSwDTO> listsw = goalswdao.getpastgoal(friendSeq, date);
        model.addAttribute("goallistsw", listsw);

        ArrayList<goalTDTO> listt = goaltdao.getpastgoal(friendSeq, date);
        model.addAttribute("goallistt", listt);

        return "mypage_pastgoal";
    }

    @RequestMapping("makefolder.do")
    public void makefolder(@RequestParam("folder") String folder, @RequestParam("folderName") String folderName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        int seq = 0;
        if (session.getAttribute("seq") != null) {
            seq = (int) session.getAttribute("seq");
        }
        if (folderName.equals(null) || folderName.equals("")) {
            folderName = "폴더";
        }
        System.out.println(folder);
        System.out.println(folderName);

        folderDAO folderdao = new folderDAO();
        folderdao.makefolder(seq, folderName, folder);
        response.getWriter().print(0);

    }

    @RequestMapping("/mypage/folder")
    public String goviewfolder(HttpServletRequest request, Model model) {
        String folderName = request.getParameter("folderName");
        HttpSession session = request.getSession();
        int seq = 0;
        if (session.getAttribute("seq") != null) {
            seq = (int) session.getAttribute("seq");
        }
        mypageDAO mypagedao = new mypageDAO();
        folderDAO folderdao = new folderDAO();
        JSONArray list = folderdao.getfolders(seq, folderName);
        ArrayList<String> dates = new ArrayList<>();
        for (int i = 0; i < list.length(); i++) {
            String date = list.get(i).toString();
            System.out.println(date);
            dates.add(date);
        }

        ArrayList<ResultSet> goalList = new ArrayList<>();
        for (int i = 0; i < dates.size(); i++) {
            try {
                ResultSet temp = mypagedao.getHighestTime(dates.get(i), seq);
                temp.next();
                goalList.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("goalList", goalList);
        model.addAttribute("folderName", folderName);

        return "mypage_folder";
    }

}
