package com.example.diary_0200.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class userDAO {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String MARIADB_URL = "jdbc:mysql://localhost:3307/diary0200?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
    static final String MARIADB_ID = "SOX_user";
    static final String MARIADB_PW = "user123";

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public userDAO() {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(MARIADB_URL, MARIADB_ID, MARIADB_PW);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int signup(userDTO user){
        String SQL = "INSERT INTO option VALUES (?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, 1);
            ps.setString(2, user.getName());
            ps.setString(3, user.getGender());
            ps.setInt(4, user.getAge());
            ps.setString(5, user.getTel());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getId());
            ps.setString(8, user.getPw());
            ps.executeUpdate();
            return 1;   //저장성공.
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;  //DB오류.
    }


}
