package com.example.diary_0200.Controller;

import com.example.diary_0200.DAO.friendDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class FriendController {

    @RequestMapping(value = "/friend/main")
    public String goFriend(HttpServletRequest request, Model model) {
        friendDAO dao = new friendDAO();

        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }


        //내 정보 (회원번호, 이름, 목표 수행 시간) 담기
        ResultSet myInfoTemp = dao.loadFriendInfo(seq);
        ArrayList<ResultSet> myInfo = new ArrayList<>();
        try {
            if (myInfoTemp.next()) {
                myInfo.add(myInfoTemp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //친구 회원번호 리스트 불러오기
        ResultSet friendSeqList = dao.loadFriendSeq(seq);
        ArrayList<Integer> friendSeq = new ArrayList<>();

        try {
            int i = 0;
            if (friendSeqList.next()) {
                do {
                    friendSeq.add(friendSeqList.getInt("friendSeq"));
                    i++;
                } while (friendSeqList.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println("현재 친구 " + friendSeq);

        //친구 정보 (회원번호, 이름, 목표 수행 시간) 담기
        ArrayList<ResultSet> friendInfo = new ArrayList<>();
        for (int i = 0; i < friendSeq.size(); i++) {
            try {
                ResultSet temp = dao.loadFriendInfo(friendSeq.get(i));
                temp.next();
                friendInfo.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("myInfo", myInfo);
        model.addAttribute("friendInfo", friendInfo);

//        try {
//            int i = 0;
//            if (friendInfo.get(i).next()) {
//                do {
//                    int seq = friendInfo.get(i).getInt("seq");
//                    String name = friendInfo.get(i).getString("name");
//                    String time = friendInfo.get(i).getString("time");
//                    System.out.println("친구 회원번호: " + seq + ", 친구 이름: " + name + ", 목표 수행시간: " + time);
//                    i++;
//                } while (friendInfo.get(i).next());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        return "friend";
    }

    @PostMapping(value = "/friend/main/sort")
    public void sortFriendList(@RequestParam("sortBy") String criteria, HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }

        friendDAO dao = new friendDAO();
        System.out.println("criteria " +criteria);





    }

    @RequestMapping(value = "/friend/edit")
    public String goFriendEdit(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }

        friendDAO dao = new friendDAO();
//        request.setCharacterEncoding("UTF-8");


        //친구 요청 회원번호 리스트 불러오기
        ResultSet friendRequestSeq = dao.loadFriendRequest(seq);

        ArrayList<Integer> friendRequestSeqList = new ArrayList<>();

        try {
            int i = 0;
            if (friendRequestSeq.next()) {
                do {
                    friendRequestSeqList.add(friendRequestSeq.getInt("friendSeq"));
                    i++;
                } while (friendRequestSeq.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //친구 요청 정보 (회원번호, 이름) 담기
        ArrayList<ResultSet> requestInfo = new ArrayList<>();
        for (int i = 0; i < friendRequestSeqList.size(); i++) {
            try {
                ResultSet temp = dao.loadFriendEditInfo(friendRequestSeqList.get(i));
                temp.next();
                requestInfo.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //친구 회원번호 리스트 불러오기
        ResultSet friendSeqList = dao.loadFriendSeq(seq);
        ArrayList<Integer> friendSeq = new ArrayList<>();

        try {
            int i = 0;
            if (friendSeqList.next()) {
                do {
                    friendSeq.add(friendSeqList.getInt("friendSeq"));
                    i++;
                } while (friendSeqList.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //친구 정보 (회원번호, 이름, 목표 수행 시간) 담기
        ArrayList<ResultSet> friendInfo = new ArrayList<>();
        for (int i = 0; i < friendSeq.size(); i++) {
            try {
                ResultSet temp = dao.loadFriendEditInfo(friendSeq.get(i));
                temp.next();
                friendInfo.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("requestInfo", requestInfo);
        model.addAttribute("friendInfo", friendInfo);


        return "friend_edit";
    }

    @PostMapping (value = "/friend/edit/search")
    public String searchFriend(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }

        friendDAO dao = new friendDAO();

        //검색하는 아이디 값 전달받기
        String friendID = request.getParameter("id");
        if (friendID!=null) {
            ResultSet friendSearchInfo = dao.searchFriend(friendID);

            try {
                if (friendSearchInfo.next()) {
                    model.addAttribute("friendSearchInfo", friendSearchInfo);
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        //친구추가 보내려는 친구 회원번호 전달받기
        String friendSeq = request.getParameter("seq");
        if (friendSeq!=null) {
            dao.sendFriendRequest(seq, Integer.parseInt(friendSeq));
        }

        //친구 요청 회원번호 리스트 불러오기
        ResultSet friendRequestSeq = dao.loadFriendRequest(seq);

        ArrayList<Integer> friendRequestSeqList = new ArrayList<>();

        try {
            int i = 0;
            if (friendRequestSeq.next()) {
                do {
                    friendRequestSeqList.add(friendRequestSeq.getInt("friendSeq"));
                    i++;
                } while (friendRequestSeq.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //친구 요청 정보 (회원번호, 이름) 담기
        ArrayList<ResultSet> requestInfo = new ArrayList<>();
        for (int i = 0; i < friendRequestSeqList.size(); i++) {
            try {
                ResultSet temp = dao.loadFriendEditInfo(friendRequestSeqList.get(i));
                temp.next();
                requestInfo.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //친구 회원번호 리스트 불러오기
        ResultSet friendSeqList = dao.loadFriendSeq(seq);
        ArrayList<Integer> friendSeqNum = new ArrayList<>();

        try {
            int i = 0;
            if (friendSeqList.next()) {
                do {
                    friendSeqNum.add(friendSeqList.getInt("friendSeq"));
                    i++;
                } while (friendSeqList.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //친구 정보 (회원번호, 이름, 목표 수행 시간) 담기
        ArrayList<ResultSet> friendInfo = new ArrayList<>();
        for (int i = 0; i < friendSeqNum.size(); i++) {
            try {
                ResultSet temp = dao.loadFriendEditInfo(friendSeqNum.get(i));
                temp.next();
                friendInfo.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("requestInfo", requestInfo);
        model.addAttribute("friendInfo", friendInfo);

        return "friend_edit";
    }


    @PostMapping (value = "/friend/edit/delete")
    public String deleteFriend(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }

        friendDAO dao = new friendDAO();

        String friendSeqNum = request.getParameter("seq");
        //친구 삭제
        dao.deleteFriend1(1, Integer.parseInt(friendSeqNum));
        dao.deleteFriend2(Integer.parseInt(friendSeqNum), seq);

        //친구 요청 회원번호 리스트 불러오기
        ResultSet friendRequestSeq = dao.loadFriendRequest(seq);

        ArrayList<Integer> friendRequestSeqList = new ArrayList<>();

        try {
            int i = 0;
            if (friendRequestSeq.next()) {
                do {
                    friendRequestSeqList.add(friendRequestSeq.getInt("friendSeq"));
                    i++;
                } while (friendRequestSeq.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //친구 요청 정보 (회원번호, 이름) 담기
        ArrayList<ResultSet> requestInfo = new ArrayList<>();
        for (int i = 0; i < friendRequestSeqList.size(); i++) {
            try {
                ResultSet temp = dao.loadFriendEditInfo(friendRequestSeqList.get(i));
                temp.next();
                requestInfo.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //친구 회원번호 리스트 불러오기
        ResultSet friendSeqList = dao.loadFriendSeq(seq);
        ArrayList<Integer> friendSeq = new ArrayList<>();

        try {
            int i = 0;
            if (friendSeqList.next()) {
                do {
                    friendSeq.add(friendSeqList.getInt("friendSeq"));
                    i++;
                } while (friendSeqList.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //친구 정보 (회원번호, 이름, 목표 수행 시간) 담기
        ArrayList<ResultSet> friendInfo = new ArrayList<>();
        for (int i = 0; i < friendSeq.size(); i++) {
            try {
                ResultSet temp = dao.loadFriendEditInfo(friendSeq.get(i));
                temp.next();
                friendInfo.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("requestInfo", requestInfo);
        model.addAttribute("friendInfo", friendInfo);

        return "friend_edit";
    }

    @PostMapping (value = "/friend/edit/requestaccept")
    public String friendRequestAccept(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }

        friendDAO dao = new friendDAO();

        String friendSeqNum = request.getParameter("seq");
        dao.acceptFriend1(seq, Integer.parseInt(friendSeqNum));
        dao.acceptFriend2(seq, Integer.parseInt(friendSeqNum));


        //친구 요청 회원번호 리스트 불러오기
        ResultSet friendRequestSeq = dao.loadFriendRequest(seq);

        ArrayList<Integer> friendRequestSeqList = new ArrayList<>();

        try {
            int i = 0;
            if (friendRequestSeq.next()) {
                do {
                    friendRequestSeqList.add(friendRequestSeq.getInt("friendSeq"));
                    i++;
                } while (friendRequestSeq.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //친구 요청 정보 (회원번호, 이름) 담기
        ArrayList<ResultSet> requestInfo = new ArrayList<>();
        for (int i = 0; i < friendRequestSeqList.size(); i++) {
            try {
                ResultSet temp = dao.loadFriendEditInfo(friendRequestSeqList.get(i));
                temp.next();
                requestInfo.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //친구 회원번호 리스트 불러오기
        ResultSet friendSeqList = dao.loadFriendSeq(seq);
        ArrayList<Integer> friendSeq = new ArrayList<>();

        try {
            int i = 0;
            if (friendSeqList.next()) {
                do {
                    friendSeq.add(friendSeqList.getInt("friendSeq"));
                    i++;
                } while (friendSeqList.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //친구 정보 (회원번호, 이름, 목표 수행 시간) 담기
        ArrayList<ResultSet> friendInfo = new ArrayList<>();
        for (int i = 0; i < friendSeq.size(); i++) {
            try {
                ResultSet temp = dao.loadFriendEditInfo(friendSeq.get(i));
                temp.next();
                friendInfo.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("requestInfo", requestInfo);
        model.addAttribute("friendInfo", friendInfo);



        return "friend_edit";
    }

    @PostMapping (value = "/friend/edit/requestrefuse")
    public String friendRequestRefuse(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        int seq = 0;
        if(session.getAttribute("seq")!=null){
            seq = (int) session.getAttribute("seq");
        }

        friendDAO dao = new friendDAO();

        String friendSeqNum = request.getParameter("seq");
        dao.refuseFriend(seq, Integer.parseInt(friendSeqNum));

        //친구 요청 회원번호 리스트 불러오기
        ResultSet friendRequestSeq = dao.loadFriendRequest(seq);

        ArrayList<Integer> friendRequestSeqList = new ArrayList<>();

        try {
            int i = 0;
            if (friendRequestSeq.next()) {
                do {
                    friendRequestSeqList.add(friendRequestSeq.getInt("friendSeq"));
                    i++;
                } while (friendRequestSeq.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //친구 요청 정보 (회원번호, 이름) 담기
        ArrayList<ResultSet> requestInfo = new ArrayList<>();
        for (int i = 0; i < friendRequestSeqList.size(); i++) {
            try {
                ResultSet temp = dao.loadFriendEditInfo(friendRequestSeqList.get(i));
                temp.next();
                requestInfo.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //친구 회원번호 리스트 불러오기
        ResultSet friendSeqList = dao.loadFriendSeq(seq);
        ArrayList<Integer> friendSeq = new ArrayList<>();

        try {
            int i = 0;
            if (friendSeqList.next()) {
                do {
                    friendSeq.add(friendSeqList.getInt("friendSeq"));
                    i++;
                } while (friendSeqList.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //친구 정보 (회원번호, 이름, 목표 수행 시간) 담기
        ArrayList<ResultSet> friendInfo = new ArrayList<>();
        for (int i = 0; i < friendSeq.size(); i++) {
            try {
                ResultSet temp = dao.loadFriendEditInfo(friendSeq.get(i));
                temp.next();
                friendInfo.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("requestInfo", requestInfo);
        model.addAttribute("friendInfo", friendInfo);

        return "friend_edit";
    }
}
