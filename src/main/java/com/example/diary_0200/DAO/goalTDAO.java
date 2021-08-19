package com.example.diary_0200.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class goalTDAO {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String MARIADB_URL = "jdbc:mysql://localhost:3307/diary0200?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
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

    public void thatdaygoal(String date){
        String sql_query = "SELECT * FROM goal_t WHERE DATE_FORMAT(date, '%Y-%m-%d') = ?";  //*,returne 값 등 수정 필요
        try{
            ps = con.prepareStatement(sql_query);
            ps.setString(1, date);
            rs = ps.executeQuery();
            while(rs.next()){
                //추가 필요.
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void thatday(int seq, String date){
        String sql_query = "SELECT * FROM goal_t WHERE DATE_FORMAT(date, '%Y-%m-%d')=? AND seq = ?";
    }




}
