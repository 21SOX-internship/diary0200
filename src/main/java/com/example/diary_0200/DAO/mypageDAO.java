package com.example.diary_0200.DAO;

import java.sql.*;

public class mypageDAO {
    private ResultSet rs; // 필드는 기본적으로 객체를 생성하면 NULL값이 들어간다.
    private Statement stmt;
    private PreparedStatement pstmt;
    private Connection conn;

    public mypageDAO(){
        try{
            String dbURL = "jdbc:mysql://localhost/diary0200";
            String dbID = "SOX_user";
            String dbPassword = "user123";
            //Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
