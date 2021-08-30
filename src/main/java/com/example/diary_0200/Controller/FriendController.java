package com.example.diary_0200.Controller;

import com.example.diary_0200.DAO.friendDAO;
import com.example.diary_0200.DAO.friendDTO;
import com.example.diary_0200.DAO.mypageDAO;
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
        if (session.getAttribute("seq") != null) {
            seq = (int) session.getAttribute("seq");
        }


        //내 정보 (회원번호, 이름, 목표 수행 시간) 담기
        ArrayList<ResultSet> myInfo = new ArrayList<>();

        if (dao.isThereGoal(seq)) {
            ResultSet myInfoTemp = dao.loadTodayFriendInfo(seq);
            try {
                if (myInfoTemp.next()) {
                    myInfo.add(myInfoTemp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ResultSet myInfoTemp = dao.loadFriendSeqAndName(seq);
            try {
                if (myInfoTemp.next()) {
                    myInfo.add(myInfoTemp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //정렬 기준 (이름순, 시간순) 받아오기
        String criteria = request.getParameter("criteria");
        System.out.println("criteria " + criteria);

        if (criteria == null) {
            criteria = "null";
        }

        if (criteria.equals("name")) {

            //친구 회원번호 리스트 불러오기 (이름순으로)
            ResultSet friendSeqList = dao.loadFriendSeqOrderByName(seq);
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

            ArrayList<friendDTO> standardFriendInfo = new ArrayList<friendDTO>();

            for (int i = 0; i < friendSeq.size(); i++) {
                ResultSet temp = dao.loadTodayFriendInfo(friendSeq.get(i));
                try {
                    if (temp.next()) {
                        friendDTO friend = new friendDTO();
                        friend.setSeq(temp.getInt("seq"));
                        friend.setTime(temp.getString("time"));
                        friend.setName(temp.getString("name"));
                        standardFriendInfo.add(friend);
                    } else {
                        ResultSet temp2 = dao.loadFriendSeqAndName(friendSeq.get(i));
                        temp2.next();
                        friendDTO friend = new friendDTO();
                        friend.setSeq(temp2.getInt("seq"));
                        friend.setName(temp2.getString("name"));
                        standardFriendInfo.add(friend);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            model.addAttribute("standardFriendInfo", standardFriendInfo);

        } else if (criteria.equals("time")) {
            ResultSet friendInfoOrderByTime = dao.loadFriendInfoOrderByTime(seq);
            ArrayList<friendDTO> standardFriendInfo = new ArrayList<friendDTO>();

            try {
                if (friendInfoOrderByTime.next()) {
                    do {
                        friendDTO friend = new friendDTO();
                        friend.setSeq(friendInfoOrderByTime.getInt("seq"));
                        friend.setTime(friendInfoOrderByTime.getString("time"));
                        friend.setName(friendInfoOrderByTime.getString("name"));
                        standardFriendInfo.add(friend);
                    } while (friendInfoOrderByTime.next());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            model.addAttribute("standardFriendInfo", standardFriendInfo);
        } else {
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

            ArrayList<friendDTO> standardFriendInfo = new ArrayList<>();
            for (int i = 0; i < friendSeq.size(); i++) {
                ResultSet temp = dao.loadTodayFriendInfo(friendSeq.get(i));
                try {
                    if (temp.next()) {
                        friendDTO friend = new friendDTO();
                        friend.setSeq(temp.getInt("seq"));
                        friend.setTime(temp.getString("time"));
                        friend.setName(temp.getString("name"));
                        standardFriendInfo.add(friend);
                    } else {
                        ResultSet temp2 = dao.loadFriendSeqAndName(friendSeq.get(i));
                        temp2.next();
                        friendDTO friend = new friendDTO();
                        friend.setSeq(temp2.getInt("seq"));
                        friend.setName(temp2.getString("name"));
                        standardFriendInfo.add(friend);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            model.addAttribute("standardFriendInfo", standardFriendInfo);
        }




        model.addAttribute("myInfo", myInfo);
        model.addAttribute("friendCount", dao.countFriendNum(seq));

        return "friend";
    }

    @PostMapping(value = "/friend/mainsort")
    public String sortFriendList(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        int seq = 0;
        if (session.getAttribute("seq") != null) {
            seq = (int) session.getAttribute("seq");
        }

        //dao 객체 생성
        friendDAO dao = new friendDAO();

//        //내 정보 (회원번호, 이름, 목표 수행 시간) 담기
//        ResultSet myInfoTemp = dao.loadTodayFriendInfo(seq);
//
//        ArrayList<ResultSet> myInfo = new ArrayList<>();
//        try {
//            if (myInfoTemp.next()) {
//                myInfo.add(myInfoTemp);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //내 정보 (회원번호, 이름, 목표 수행 시간) 담기
        ArrayList<ResultSet> myInfo = new ArrayList<>();

        if (dao.isThereGoal(seq)) {
            ResultSet myInfoTemp = dao.loadTodayFriendInfo(seq);
            try {
                if (myInfoTemp.next()) {
                    myInfo.add(myInfoTemp);
                    model.addAttribute("myInfo", myInfo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ResultSet myInfoTemp = dao.loadFriendSeqAndName(seq);
            try {
                if (myInfoTemp.next()) {
                    myInfo.add(myInfoTemp);
                    model.addAttribute("myInfo", myInfo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //정렬 기준 (이름순, 시간순) 받아오기
        String criteria = request.getParameter("criteria");
        System.out.println("criteria " + criteria);

        if (criteria.equals("name")) {

            //친구 회원번호 리스트 불러오기 (이름순으로)
            ResultSet friendSeqList = dao.loadFriendSeqOrderByName(seq);
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

            ArrayList<friendDTO> standardFriendInfo = new ArrayList<friendDTO>();

            for (int i = 0; i < friendSeq.size(); i++) {
                ResultSet temp = dao.loadTodayFriendInfo(friendSeq.get(i));
                try {
                    if (temp.next()) {
                        friendDTO friend = new friendDTO();
                        friend.setSeq(temp.getInt("seq"));
                        friend.setTime(temp.getString("time"));
                        friend.setName(temp.getString("name"));
                        standardFriendInfo.add(friend);
                    } else {
                        ResultSet temp2 = dao.loadFriendSeqAndName(friendSeq.get(i));
                        temp2.next();
                        friendDTO friend = new friendDTO();
                        friend.setSeq(temp2.getInt("seq"));
                        friend.setName(temp2.getString("name"));
                        standardFriendInfo.add(friend);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            model.addAttribute("standardFriendInfo", standardFriendInfo);


        } else if (criteria.equals("time")) {
            ResultSet friendInfoOrderByTime = dao.loadFriendInfoOrderByTime(seq);
            ArrayList<friendDTO> standardFriendInfo = new ArrayList<friendDTO>();

            try {
                if (friendInfoOrderByTime.next()) {
                    do {
                        friendDTO friend = new friendDTO();
                        friend.setSeq(friendInfoOrderByTime.getInt("seq"));
                        friend.setTime(friendInfoOrderByTime.getString("time"));
                        friend.setName(friendInfoOrderByTime.getString("name"));
                        standardFriendInfo.add(friend);
                    } while (friendInfoOrderByTime.next());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            model.addAttribute("standardFriendInfo", standardFriendInfo);
        }


        model.addAttribute("friendCount", dao.countFriendNum(seq));
//        model.addAttribute("myInfo", myInfo);
        return "friend";
    }

    @RequestMapping(value = "/friend/edit")
    public String goFriendEdit(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        int seq = 0;
        if (session.getAttribute("seq") != null) {
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
        model.addAttribute("friendCount", dao.countFriendNum(seq));


        return "friend_edit";
    }

    @PostMapping(value = "/friend/edit/search")
    public String searchFriend(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        int seq = 0;
        if (session.getAttribute("seq") != null) {
            seq = (int) session.getAttribute("seq");
        }

        friendDAO dao = new friendDAO();

        //검색하는 아이디 값 전달받기
        String friendID = request.getParameter("id");
        if (friendID != null) {
            ResultSet friendSearchInfo = dao.searchFriend(friendID);

            try {
                if (friendSearchInfo.next()) {
                    model.addAttribute("friendSearchInfo", friendSearchInfo);
                }
            } catch (SQLException e) {
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

        //친구추가 보내려는 친구 회원번호 전달받기
        String friendSeq = request.getParameter("seq");
        if (friendSeq != null) {
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

        model.addAttribute("requestInfo", requestInfo);
        model.addAttribute("friendInfo", friendInfo);

        return "friend_edit";
    }


    @PostMapping(value = "/friend/edit/delete")
    public String deleteFriend(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        int seq = 0;
        if (session.getAttribute("seq") != null) {
            seq = (int) session.getAttribute("seq");
        }

        friendDAO dao = new friendDAO();

        String friendSeqNum = request.getParameter("seq");
        //친구 삭제
        dao.deleteFriend1(seq, Integer.parseInt(friendSeqNum));
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

        model.addAttribute("msg", "친구가 삭제되었습니다.");
        model.addAttribute("url", "/friend/edit");

        return "alert";
    }

    @PostMapping(value = "/friend/edit/requestaccept")
    public String friendRequestAccept(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        int seq = 0;
        if (session.getAttribute("seq") != null) {
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

        model.addAttribute("msg", "친구 요청을 수락하였습니다.");
        model.addAttribute("url", "/friend/edit");

        return "alert";

    }

    @PostMapping(value = "/friend/edit/requestrefuse")
    public String friendRequestRefuse(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        int seq = 0;
        if (session.getAttribute("seq") != null) {
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

        model.addAttribute("msg", "친구 요청을 거절하였습니다.");
        model.addAttribute("url", "/friend/edit");

        return "alert";
    }

    @RequestMapping(value = "/friend/page")
    public String gofriendpage(HttpServletRequest request, Model model) {


        int seq = Integer.parseInt(request.getParameter("friendSeq"));
        System.out.println("friendSeq : " + seq);
        model.addAttribute("friendSeq", seq);

        //dao 객체 생성
        mypageDAO dao = new mypageDAO();


        String mypageName = dao.loadMyPageName(seq);
        System.out.println("friend name : "+mypageName);

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

        model.addAttribute("mypageName", mypageName);
        model.addAttribute("mypageInfo", mypageInfo);
        model.addAttribute("goalList", goalList);

        return "friend_page";
    }
}
