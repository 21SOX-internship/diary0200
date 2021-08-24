package com.example.diary_0200.Controller;

import com.example.diary_0200.DAO.mypageDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;

@Controller
public class MyPageController {

    @RequestMapping(value = "/mypage/main")
    public String gomypage(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }

        mypageDAO dao = new mypageDAO();

        ResultSet mypageInfo = dao.loadMyPageInfo(seq);
        try {
            mypageInfo.next();
        } catch (Exception e) {
            e.printStackTrace();
        }


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

//        /*테스트 코드*/
//        for(int i=0; i<dateList.size(); i++) {
//            System.out.println("date : "+dateList.get(i));
//        }

        //해당 날짜 중에서 수행시간이 가장 높은 목표 추출
        ArrayList<ResultSet> goalList = new ArrayList<>();
        for(int i=0; i<dateList.size(); i++) {
            try {
                ResultSet temp = dao.getHighestTime(dateList.get(i));
                temp.next();
                goalList.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

//        /*테스트코드*/
//        for(int i=0; i<goalList.size(); i++) {
//            try{
//                if(goalList.get(i).next()){
//                    System.out.println("goal : "+goalList.get(i).getString("date"));
//                    System.out.println("goal : "+goalList.get(i).getString("time"));
//
//                }
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }

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

    @RequestMapping(value = "/mypage/main/goals")
    public String gomypagegoal(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }

        String date = request.getParameter("date");
        System.out.println("date: "+date);



        return "mypage_goal";
    }
}
