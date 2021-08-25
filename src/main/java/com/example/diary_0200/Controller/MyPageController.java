package com.example.diary_0200.Controller;

import com.example.diary_0200.DAO.mypageDAO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
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
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }
        System.out.println("seq : " +seq);

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
            if(date.next()) {
                do {
                    dateList.add(date.getString("date"));
                } while (date.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*테스트 코드*/
        for(int i=0; i<dateList.size(); i++) {
            System.out.println("회원번호별 날짜 추출 date : "+dateList.get(i));
        }

        //해당 날짜 중에서 수행시간이 가장 높은 목표 추출
        ArrayList<ResultSet> goalList = new ArrayList<>();
        for(int i=0; i<dateList.size(); i++) {
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
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }

        mypageDAO dao = new mypageDAO();

        ResultSet mypageInfo = dao.loadMyPageInfo(seq);

        //회원번호별 날짜 추출
        ResultSet date = dao.getDate(seq);

        ArrayList<String> dateList = new ArrayList<>();
        try {
            if(date.next()) {
                do {
                    dateList.add(date.getString("date"));
                } while (date.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*테스트 코드*/
        for(int i=0; i<dateList.size(); i++) {
            System.out.println("회원번호별 날짜 추출 date : "+dateList.get(i));
        }

        //해당 날짜 중에서 수행시간이 가장 높은 목표 추출
        ArrayList<ResultSet> goalList = new ArrayList<>();
        for(int i=0; i<dateList.size(); i++) {
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
    public String upload(HttpServletRequest request, @RequestBody MultipartFile uploadFile, Model model) {

        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }

        mypageDAO dao = new mypageDAO();
        String realPath = request.getServletContext().getRealPath("/upload");

        String message = request.getParameter("message");
        System.out.println("message : "+message);

        dao.saveMessage(seq, message);
        System.out.println(realPath);

        File currentDirPath = new File(realPath);
        System.out.println(currentDirPath);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(currentDirPath);
        factory.setSizeThreshold(1024*1024*5);
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List items = upload.parseRequest(request);
            FileItem fileItem = (FileItem) items.get(0);


            File uploadFile1 = new File(currentDirPath+"\\"+seq+".png");
//            File uploadFile = new File("\\이름.png");
            fileItem.write(uploadFile1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //마이페이지 이름 불러오기
        String mypageName = dao.loadMyPageName(seq);

        //마이페이지 회원번호, 상태메시지 불러오기
        ResultSet mypageInfo = dao.loadMyPageInfo(seq);


        //회원번호별 날짜 추출
        ResultSet date = dao.getDate(seq);

        ArrayList<String> dateList = new ArrayList<>();
        try {
            if(date.next()) {
                do {
                    dateList.add(date.getString("date"));
                } while (date.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*테스트 코드*/
        for(int i=0; i<dateList.size(); i++) {
            System.out.println("회원번호별 날짜 추출 date : "+dateList.get(i));
        }

        //해당 날짜 중에서 수행시간이 가장 높은 목표 추출
        ArrayList<ResultSet> goalList = new ArrayList<>();
        for(int i=0; i<dateList.size(); i++) {
            try {
                ResultSet temp = dao.getHighestTime(dateList.get(i), seq);
                temp.next();
                goalList.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("uploadPath", realPath);

        model.addAttribute("mypageName", mypageName);
        model.addAttribute("mypageInfo", mypageInfo);
        model.addAttribute("goalList", goalList);

        return "mypage";
    }
}
