package com.example.diary_0200.Controller;

import com.example.diary_0200.DAO.friendDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.util.ArrayList;

@Controller
public class FriendController {

    @RequestMapping(value = "/friend/main")
    public String goFriend(HttpServletRequest request, Model model) {
        friendDAO dao = new friendDAO();

        //내 정보 (회원번호, 이름, 목표 수행 시간) 담기
        ResultSet myInfoTemp = dao.loadFriendInfo(1);
        ArrayList<ResultSet> myInfo = new ArrayList<>();
        try {
            if (myInfoTemp.next()) {
                myInfo.add(myInfoTemp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //친구 회원번호 리스트 불러오기
        ResultSet friendSeqList = dao.loadFriendSeq(1);
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

        System.out.println(friendSeq);

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

    @RequestMapping(value = "/friend/edit")
    public String goFriendEdit(HttpServletRequest request, Model model) {
        friendDAO dao = new friendDAO();

//        //친구 요청 회원번호 리스트 불러오기
//        ResultSet friendRequestSeq = dao.loadFriendRequest(1);
//        ArrayList<Integer> friendRequestSeqList = new ArrayList<>();
//
//        try {
//            int i = 0;
//            if (friendRequestSeq.next()) {
//                do {
//                    friendRequestSeqList.add(friendRequestSeq.getInt("friendSeq"));
//                    i++;
//                } while (friendRequestSeq.next());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        //친구 요청 정보 (회원번호, 이름) 담기
//        ArrayList<ResultSet> requestInfo = new ArrayList<>();
//        for (int i = 0; i < friendRequestSeqList.size(); i++) {
//            try {
//                ResultSet temp = dao.loadFriendInfo(friendRequestSeqList.get(i));
//                temp.next();
//                requestInfo.add(temp);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        if(requestInfo == null) {
//            System.out.println("requestInfo is null");
//        } else {
//            System.out.println("requestInfo is not null");
//        }

        //친구 회원번호 리스트 불러오기
        ResultSet friendSeqList = dao.loadFriendSeq(1);
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
                ResultSet temp = dao.loadFriendInfo(friendSeq.get(i));
                temp.next();
                friendInfo.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        model.addAttribute("requestInfo", requestInfo);
        model.addAttribute("friendInfo", friendInfo);


        return "friend_edit";
    }

    @PostMapping (value = "/friend/edit/delete")
    public String deleteFriend(HttpServletRequest request, Model model) {
        friendDAO dao = new friendDAO();

        String friendSeqNum = request.getParameter("seq");
        System.out.println("friendSeqNum : "+friendSeqNum);

        //친구 삭제
        dao.deleteFriend(1, Integer.parseInt(friendSeqNum));

        //친구 회원번호 리스트 불러오기
        ResultSet friendSeqList = dao.loadFriendSeq(1);
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
                ResultSet temp = dao.loadFriendInfo(friendSeq.get(i));
                temp.next();
                friendInfo.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("friendInfo", friendInfo);

        return "friend_edit";
    }

    @PostMapping (value = "/friend/edit/request")
    public String friendRequest(HttpServletRequest request, Model model) {
        friendDAO dao = new friendDAO();

        ResultSet friendRequestSeq = dao.loadFriendRequest(1);

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

        //친구 정보 (회원번호, 이름, 목표 수행 시간) 담기
        ArrayList<ResultSet> requestInfo = new ArrayList<>();
        for (int i = 0; i < friendRequestSeqList.size(); i++) {
            try {
                ResultSet temp = dao.loadFriendInfo(friendRequestSeqList.get(i));
                temp.next();
                requestInfo.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("friendInfo", requestInfo);

        return "friend_edit";
    }
}
