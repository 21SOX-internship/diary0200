package com.example.diary_0200.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class goalSwDAO {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String MARIADB_URL = "jdbc:mysql://localhost:3307/diary0200?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
    static final String MARIADB_ID = "SOX_user";
    static final String MARIADB_PW = "user123";

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public goalSwDAO() {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(MARIADB_URL, MARIADB_ID, MARIADB_PW);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean istheregoal(int seq){
        String sql_query = "SELECT * FROM goal_sw WHERE DATE_FORMAT(date, '%Y-%m-%d')=DATE_FORMAT(NOW(), '%Y-%m-%d') AND seq = ?";
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

    public ArrayList<goalSwDTO> gettoodaysgoal(int seq){
        String sql_query = "SELECT * FROM goal_sw WHERE DATE_FORMAT(date, '%Y-%m-%d')=DATE_FORMAT(NOW(), '%Y-%m-%d') AND seq = ?";
        ArrayList<goalSwDTO> list = new ArrayList<goalSwDTO>();
        try{
            ps = con.prepareStatement(sql_query);
            ps.setInt(1,seq);
            rs = ps.executeQuery();
            while(rs.next()){
                goalSwDTO goalswdto = new goalSwDTO();
                goalswdto.setTime(rs.getString("time"));
                goalswdto.setGoalName(rs.getString("goalName"));
                goalswdto.setTag(rs.getString("tag"));
                list.add(goalswdto);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
