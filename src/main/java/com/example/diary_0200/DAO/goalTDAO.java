package com.example.diary_0200.DAO;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class goalTDAO {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String MARIADB_URL = "jdbc:mysql://localhost:3306/diary0200?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
    static final String MARIADB_ID = "SOX_user";
    static final String MARIADB_PW = "user123";

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public goalTDAO() {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(MARIADB_URL, MARIADB_ID, MARIADB_PW);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean istheregoal(int seq){
        String sql_query = "SELECT * FROM goal_t WHERE DATE_FORMAT(date, '%Y-%m-%d')=DATE_FORMAT(NOW(), '%Y-%m-%d') AND seq = ?";
        try{
            ps = con.prepareStatement(sql_query);
            ps.setInt(1,seq);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;    //목표가 있으면 true
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;       //아니면 false
    }


    public int updategoal(goalTDTO goaldata, goalTDTO chageddata){
        String sql_query = "UPDATE goal_t SET tag = ?, goalName = ? WHERE  DATE_FORMAT(date, '%Y-%m-%d')=DATE_FORMAT(NOW(), '%Y-%m-%d') AND seq = ? AND goalName = ? AND tag = ? LIMIT 1";
        try{
            ps = con.prepareStatement(sql_query);
            ps.setString(1, chageddata.getTag());
            ps.setString(2, chageddata.getGoalName());
            ps.setInt(3, goaldata.getSeq());
            ps.setString(4,goaldata.getGoalName());
            ps.setString(5, goaldata.getTag());
            ps.executeUpdate();
            return 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public JSONObject getrecordingtime(int seq, String goalName, String goalTag){
        String sql_query = "SELECT * FROM goal_t WHERE seq=? AND goalName = ? AND tag= ? AND DATE_FORMAT(date, '%Y-%m-%d')=DATE_FORMAT(NOW(), '%Y-%m-%d')";
        try{
            ps = con.prepareStatement(sql_query);
            ps.setInt(1, seq);
            ps.setString(2, goalName);
            ps.setString(3, goalTag);
            rs = ps.executeQuery();
            if(rs.next()){
                JSONObject jobj = new JSONObject();
                jobj.put("time",rs.getString("time"));
                jobj.put("endTime", rs.getString("endTime"));
                return jobj;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int updaterecord(String time, int seq, String goalName, String goalTag){
        String sql_query="UPDATE goal_t SET time=? WHERE  DATE_FORMAT(date, '%Y-%m-%d')=DATE_FORMAT(NOW(), '%Y-%m-%d') AND seq = ? AND goalName = ? AND tag = ? LIMIT 1";
        try{
            ps = con.prepareStatement(sql_query);
            ps.setString(1, time);
            ps.setInt(2, seq);
            ps.setString(3, goalName);
            ps.setString(4, goalTag);
            ps.executeUpdate();
            return 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public ArrayList<goalTDTO> gettoodaysgoal(int seq){
        String sql_query = "SELECT * FROM goal_t WHERE DATE_FORMAT(date, '%Y-%m-%d')=DATE_FORMAT(NOW(), '%Y-%m-%d') AND seq = ?";
        ArrayList<goalTDTO> list = new ArrayList<goalTDTO>();
        try{
            ps = con.prepareStatement(sql_query);
            ps.setInt(1,seq);
            rs = ps.executeQuery();
            while(rs.next()){
                goalTDTO goaltdto = new goalTDTO();
                goaltdto.setTime(rs.getString("time"));
                goaltdto.setGoalName(rs.getString("goalName"));
                goaltdto.setTag(rs.getString("tag"));
                list.add(goaltdto);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<goalTDTO> getpastgoal(int seq,String date){
        String sql_query = "SELECT * FROM goal_t WHERE DATE_FORMAT(date, '%Y-%m-%d')=? AND seq = ?";
        ArrayList<goalTDTO> list = new ArrayList<goalTDTO>();
        try{
            ps = con.prepareStatement(sql_query);
            ps.setString(1, date);
            ps.setInt(2,seq);
            rs = ps.executeQuery();
            while(rs.next()){
                goalTDTO goaltdto = new goalTDTO();
                goaltdto.setTime(rs.getString("time"));
                goaltdto.setGoalName(rs.getString("goalName"));
                goaltdto.setTag(rs.getString("tag"));
                list.add(goaltdto);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public goalTDTO getthisgoal(int seq, String goalName){
        String sql_query = "SELECT * FROM goal_t WHERE DATE_FORMAT(date, '%Y-%m-%d')=DATE_FORMAT(NOW(), '%Y-%m-%d') AND seq = ? AND goalName=?";
        goalTDTO goaltdto = new goalTDTO();
        try{
            ps = con.prepareStatement(sql_query);
            ps.setInt(1, seq);
            ps.setString(2, goalName);
            rs = ps.executeQuery();
            if(rs.next()){
                goaltdto.setTag(rs.getString("tag"));
                goaltdto.setEndTime(rs.getString("endTime"));
                return goaltdto;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int savegoal(goalTDTO goaldata){
        String sql_query = "INSERT INTO goal_t VALUES(?,NOW(),?,?,?,?)";
        try{
            ps = con.prepareStatement(sql_query);
            ps.setInt(1, goaldata.getSeq());
            ps.setString(2, goaldata.getTag());
            ps.setString(3, goaldata.getGoalName());
            ps.setString(4,goaldata.getEndTime());
            ps.setString(5,"00:00:00");
            ps.executeUpdate();
            return 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public int removegoal(int seq, String goalName, String goalTag){
        String sql_query="DELETE FROM goal_t WHERE seq=? AND DATE_FORMAT(date, '%Y-%m-%d')=DATE_FORMAT(NOW(), '%Y-%m-%d') AND goalName = ? AND tag = ? LIMIT 1";
        try{
            ps = con.prepareStatement(sql_query);
            ps.setInt(1, seq);
            ps.setString(2, goalName);
            ps.setString(3, goalTag);
            ps.executeUpdate();
            return 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

}
